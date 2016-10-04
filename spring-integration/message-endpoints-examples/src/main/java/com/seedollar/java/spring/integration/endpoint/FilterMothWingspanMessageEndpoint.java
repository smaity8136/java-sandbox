package com.seedollar.java.spring.integration.endpoint;

import com.seedollar.java.spring.integration.domain.Moth;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * Created by seedollar on 10/4/16.
 */
@MessageEndpoint
public class FilterMothWingspanMessageEndpoint {

    @Filter(inputChannel = "secondFilterChannel", outputChannel = "completeFilterChannel", discardChannel = "discardFilterChannel")
    public boolean mothWingspanGreaterThanSeven(Moth moth) {
        if (moth.getWingSpan() > 7) {
            return true;
        }
        return false;
    }
}
