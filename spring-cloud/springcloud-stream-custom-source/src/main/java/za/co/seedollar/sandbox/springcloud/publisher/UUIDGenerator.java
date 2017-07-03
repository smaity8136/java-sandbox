package za.co.seedollar.sandbox.springcloud.publisher;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.InboundChannelAdapter;
import za.co.seedollar.sandbox.springcloud.custom.MyUUIDGeneratorSource;

import java.util.UUID;

/**
 * Created by seedollar on 7/3/17.
 */
@EnableBinding(MyUUIDGeneratorSource.class)
public class UUIDGenerator {

    @InboundChannelAdapter(value = MyUUIDGeneratorSource.OUTPUT)
    public String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
