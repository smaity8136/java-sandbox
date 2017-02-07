package com.seedollar.java.websockets.controller;

import com.seedollar.java.websockets.message.Notification;
import com.seedollar.java.websockets.message.Winner;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by seedollar on 2/7/17.
 */
@Controller
public class NotificationController {

    @MessageMapping("/notification")
    @SendTo("/topic/notifications")
    public Notification notification(Winner winner) throws InterruptedException {
        Thread.sleep(1000);
        return new Notification(winner.getName());
    }
}
