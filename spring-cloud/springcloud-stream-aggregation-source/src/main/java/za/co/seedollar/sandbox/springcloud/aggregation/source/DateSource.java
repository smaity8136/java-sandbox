package za.co.seedollar.sandbox.springcloud.aggregation.source;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

/**
 * Created by seedollar on 7/19/17.
 */
public interface DateSource {

    final String OUTPUT = "dateChannel";

    @Output(DateSource.OUTPUT)
    MessageChannel dateOutput();
}
