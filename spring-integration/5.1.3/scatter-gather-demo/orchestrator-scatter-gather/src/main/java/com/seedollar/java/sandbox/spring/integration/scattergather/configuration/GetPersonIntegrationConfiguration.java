package com.seedollar.java.sandbox.spring.integration.scattergather.configuration;

import com.seedollar.java.sandbox.spring.integration.scattergather.domain.CapuletAttributes;
import com.seedollar.java.sandbox.spring.integration.scattergather.domain.MontagueAttributes;
import com.seedollar.java.sandbox.spring.integration.scattergather.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.scheduling.PollerMetadata;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.support.PeriodicTrigger;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@Slf4j
public class GetPersonIntegrationConfiguration {

    @Bean
    public MessagingTemplate getPersonMessagingTemplate() {
        return new MessagingTemplate(inputGetPersonChannel());
    }

    @Bean
    public MessageChannel inputGetPersonChannel() {
        return new DirectChannel();
    }

    @Bean
    public IntegrationFlow getPersonIntegrationFlow() {
        return IntegrationFlows.from(inputGetPersonChannel()).scatterGather(
                scatter ->
                        scatter
                                .defaultOutputChannel("outputPersonAggregationChannel")
                                .applySequence(true)
                                .recipient(inputCapuletPersonChannel())
                                .recipient(inputMontaguePersonChannel()),
                gather ->
                        gather.outputProcessor(messageGroupProcessor -> {
                            Collection<Message<?>> messages = messageGroupProcessor.getMessages();
                            Message<Person> resultMessage = MessageBuilder.createMessage(new Person(), new MessageHeaders(new HashMap<>()));
                            messages.stream().reduce(resultMessage, (target, candidateMessage) -> {
                                if (candidateMessage.getHeaders().containsKey("capuletId")) {
                                    resultMessage.getPayload().setCapuletAttributes((CapuletAttributes) candidateMessage.getPayload());
                                } else if (candidateMessage.getHeaders().containsKey("montagueId")) {
                                    resultMessage.getPayload().setMontagueAttributes((MontagueAttributes) candidateMessage.getPayload());
                                }
                                return resultMessage;
                            }, (a, b) -> a);
                            return resultMessage;
                        }))
                .get();
    }

    @Bean
    public MessageChannel inputCapuletPersonChannel() {
        return new QueueChannel();
    }

    @Bean
    public MessageChannel inputMontaguePersonChannel() {
        return new QueueChannel();
    }

    @ServiceActivator(inputChannel = "inputCapuletPersonChannel")
    public Message<?> fetchCapuletAttributes() throws InterruptedException {
        log.info("fetching capulet attributes");
        CapuletAttributes payload = new CapuletAttributes();
        payload.setSize(994);
        payload.setSword("Razor");

        Map<String, Object> headers = new HashMap<>();
        headers.put("capuletId", ThreadLocalRandom.current().nextLong());
        Thread.sleep(100);
        return MessageBuilder.createMessage(payload, new MessageHeaders(headers));
    }

    @ServiceActivator(inputChannel = "inputMontaguePersonChannel")
    public Message<?> fetchMontagueAttributes() throws InterruptedException {
        log.info("fetching montague attributes");
        MontagueAttributes payload = new MontagueAttributes();
        payload.setGun("Glock");
        payload.setCaliber("9mm");

        Map<String, Object> headers = new HashMap<>();
        headers.put("montagueId", ThreadLocalRandom.current().nextLong());

        Thread.sleep(70);
        return MessageBuilder.createMessage(payload, new MessageHeaders(headers));
    }

    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata defaultPoller() {
        PollerMetadata pollerMetadata = new PollerMetadata();
        pollerMetadata.setTrigger(new PeriodicTrigger(1));
        return pollerMetadata;
    }
}
