package com.antipin.notifications.service;

import com.antipin.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    @KafkaListener(topics = "sent_orders", groupId = "notifications_group")
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000))
    public void notify(OrderDto order) {
        log.info("{} was sent", order);
        notifyUser(order);
    }

    private void notifyUser(OrderDto order) {
        System.out.println(order.toString().concat(" was sent"));
    }
}
