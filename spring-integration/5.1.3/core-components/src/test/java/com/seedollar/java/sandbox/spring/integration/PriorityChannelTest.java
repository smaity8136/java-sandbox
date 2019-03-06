package com.seedollar.java.sandbox.spring.integration;

import com.seedollar.java.sandbox.spring.intergration.comparator.MembershipComparator;
import com.seedollar.java.sandbox.spring.intergration.enumeration.MembershipEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.integration.channel.PriorityChannel;
import org.springframework.messaging.support.MessageBuilder;

public class PriorityChannelTest {

    @Test
    @DisplayName("Priority Channel example")
    public void testPriorityChannel() {
        // Given a custom Comparator, we want to illustrate how messages being queued will be prioritized accordingly.
        PriorityChannel priorityChannel = new PriorityChannel(new MembershipComparator());
        priorityChannel.send(MessageBuilder.withPayload(MembershipEnum.GOLD).build());
        priorityChannel.send(MessageBuilder.withPayload(MembershipEnum.PLATINUM).build());
        priorityChannel.send(MessageBuilder.withPayload(MembershipEnum.BRONZE).build());
        priorityChannel.send(MessageBuilder.withPayload(MembershipEnum.SILVER).build());

        // The messages received should be according to the order defined by the Comparator.
        Assertions.assertEquals(MembershipEnum.BRONZE, priorityChannel.receive().getPayload());
        Assertions.assertEquals(MembershipEnum.SILVER, priorityChannel.receive().getPayload());
        Assertions.assertEquals(MembershipEnum.GOLD, priorityChannel.receive().getPayload());
        Assertions.assertEquals(MembershipEnum.PLATINUM, priorityChannel.receive().getPayload());
    }
}
