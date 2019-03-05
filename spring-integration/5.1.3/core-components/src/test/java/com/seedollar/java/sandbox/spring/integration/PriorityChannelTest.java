package com.seedollar.java.sandbox.spring.integration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.messaging.Message;

import java.util.Comparator;

public class PriorityChannelTest {

    @Test
    @DisplayName("Priority Channel example")
    public void testPriorityChannel() {
        PriorityChannel priorityChannel = new PriorityChannel(new MembershipComparator());
    }


}

class MembershipComparator implements Comparator<Message<MembershipEnum>> {

    public MembershipComparator() {
    }

    @Override
    public int compare(Message<MembershipEnum> o1, Message<MembershipEnum> o2) {
        return Integer.compare(o1.getPayload().ordinal(), o2.getPayload().ordinal());
    }
}

enum MembershipEnum {
    BRONZE, SILVER, GOLD, PLATINUM;
}