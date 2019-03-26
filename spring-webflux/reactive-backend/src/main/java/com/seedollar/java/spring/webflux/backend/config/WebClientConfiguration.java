package com.seedollar.java.spring.webflux.backend.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.LoggingCodecSupport;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.net.ssl.SSLException;

@Configuration
public class WebClientConfiguration {

    @Bean
    public TcpClient webClientTcpClient(
            SslContext sslContext) {
        return TcpClient.create()
                .secure(sslProvider -> sslProvider.sslContext(sslContext))
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Integer.valueOf(1000))
                .doOnConnected(connection ->
                        connection.addHandlerLast(new ReadTimeoutHandler(Integer.valueOf(60)))
                                .addHandlerLast(new WriteTimeoutHandler(Integer.valueOf(10))));
    }

    @Bean
    public WebClient webClient(TcpClient webClientTcpClient, ExchangeStrategies exchangeStrategies) {
        return WebClient.builder()
                .exchangeStrategies(exchangeStrategies)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(webClientTcpClient)))
                .baseUrl("http://localhost:8081")
                .build();
    }

    @Bean
    public SslContext sslContext() throws SSLException {
        return SslContextBuilder.forClient().build();
    }

    @Bean
    public ExchangeStrategies exchangeStrategies() {
        ExchangeStrategies exchangeStrategies = ExchangeStrategies.withDefaults();
        exchangeStrategies
                .messageWriters().stream().filter(LoggingCodecSupport.class::isInstance).
                forEach(writer -> ((LoggingCodecSupport) writer).setEnableLoggingRequestDetails(true)); // Only set this to true when debugging web service calls.
        return exchangeStrategies;
    }
}
