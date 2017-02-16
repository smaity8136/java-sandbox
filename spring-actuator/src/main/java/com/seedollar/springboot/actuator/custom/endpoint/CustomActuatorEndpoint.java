package com.seedollar.springboot.actuator.custom.endpoint;

import com.seedollar.springboot.actuator.custom.domain.LocaleInfo;
import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;

/**
 * Created by seedollar on 2/16/17.
 */
@Component
public class CustomActuatorEndpoint extends AbstractEndpoint<LocaleInfo> {

    public CustomActuatorEndpoint() {
        super("customEndpoint");
    }

    @Override
    public LocaleInfo invoke() {
        Locale.getDefault().getCountry();
        return new LocaleInfo( Locale.getDefault().getCountry(), LocalDateTime.now().toString());
    }
}
