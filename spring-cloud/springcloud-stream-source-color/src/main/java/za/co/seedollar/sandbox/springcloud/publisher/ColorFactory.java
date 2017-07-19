package za.co.seedollar.sandbox.springcloud.publisher;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import za.co.seedollar.sandbox.springcloud.source.ColorSource;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by seedollar on 7/18/17.
 */
@EnableBinding(ColorSource.class)
public class ColorFactory {

    static final List<String> COLORS = Lists.newArrayList("RED", "GREEN", "BLUE", "ORANGE", "YELLOW");

    @InboundChannelAdapter(value = ColorSource.OUTPUT, poller = @Poller(fixedRate = "2000"))
    public Message<String> publishRandomColor() {
        Map<String, Object> headers = Maps.newHashMap();
        String randomColor = COLORS.get(new Random().nextInt(COLORS.size()));
        headers.put("mood", randomColor);

        MessageHeaders messageHeaders = new MessageHeaders(headers);
        return MessageBuilder.createMessage("A mood with color: " + randomColor, messageHeaders);
    }
}
