package com.antipin.orders.controller;

import com.antipin.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    @PostMapping("/create")
    private ResponseEntity<OrderDto> create(@RequestBody OrderDto orderdto) {
        CompletableFuture<SendResult<String, OrderDto>> result = kafkaTemplate.send("new_orders", orderdto);
        result.whenComplete((res, ex) -> {
            if (ex != null) {
                throw new RuntimeException("Cannot send the message: " + orderdto);
            }
        });
        return ResponseEntity.ok(orderdto);
    }
}
