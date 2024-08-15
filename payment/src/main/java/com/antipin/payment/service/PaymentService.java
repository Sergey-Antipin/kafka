package com.antipin.payment.service;

import com.antipin.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentService {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    @KafkaListener(topics = "new_orders", groupId = "payment_group", concurrency = "3")
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000))
    public void pay(OrderDto order) {
        log.info("Payment for order: {}", order);
        if (order.getUser().equals("error")) {
            throw new RuntimeException("Error");
        }
        kafkaTemplate.send("payed_orders", order);
    }
}
