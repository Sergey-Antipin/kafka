package com.antipin.shipping.service;

import com.antipin.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShippingService {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    @KafkaListener(topics = "payed_orders", groupId = "shipping_group")
    @RetryableTopic(attempts = "3", backoff = @Backoff(delay = 1000))
    public void shipping(OrderDto order) {
        log.info("Shipping order: {}", order);
        kafkaTemplate.send("sent_orders", order);
    }
}
