package com.seedollar.java.spring.integration.endpoint;

import com.seedollar.java.spring.integration.domain.Moth;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.MessageEndpoint;

/**
 * Created by seedollar on 10/4/16.
 */
@MessageEndpoint
public class FilterMothColourMessageEndpoint {

    @Filter(inputChannel = "startFilterChannel", outputChannel = "secondFilterChannel", discardChannel = "discardFilterChannel")
    public boolean mothColourIsBlack(Moth moth) {
        if (moth.getColour().equalsIgnoreCase("BLACK")) {
            return true;
        }
        return false;
    }



}
