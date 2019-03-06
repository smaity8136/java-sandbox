package com.seedollar.java.sandbox.spring.intergration.comparator;

import com.seedollar.java.sandbox.spring.intergration.enumeration.MembershipEnum;
import org.springframework.messaging.Message;

import java.util.Comparator;

public class MembershipComparator implements Comparator<Message<?>> {

    @Override
    public int compare(Message<?> o1, Message<?> o2) {
        return Integer.compare(((MembershipEnum) o1.getPayload()).ordinal(), ((MembershipEnum) o2.getPayload()).ordinal());
    }
}


