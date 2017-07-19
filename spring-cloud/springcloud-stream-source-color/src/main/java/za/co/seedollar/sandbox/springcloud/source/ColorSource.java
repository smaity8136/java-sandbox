package za.co.seedollar.sandbox.springcloud.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/18/17.
 */
public interface ColorSource {

    final String OUTPUT = "colorChannel";

    @Output(ColorSource.OUTPUT)
    MessageChannel colorOutput();
}
