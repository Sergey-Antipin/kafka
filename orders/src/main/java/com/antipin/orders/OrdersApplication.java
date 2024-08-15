package com.antipin.orders;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@SpringBootApplication
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }

    @Value("${NEW_ORDERS_TOPIC_PARTITIONS:3}")
    private int NEW_ORDERS_TOPIC_PARTITIONS;

    @Value("${PAYED_ORDERS_TOPIC_PARTITIONS:3}")
    private int PAYED_ORDERS_TOPIC_PARTITIONS;

    @Value("${SENT_ORDERS_TOPIC_PARTITIONS:3}")
    private int SENT_ORDERS_TOPIC_PARTITIONS;

    @Bean
    public KafkaAdmin.NewTopics topics() {
        return new KafkaAdmin.NewTopics(
                TopicBuilder.name("new_orders")
                        .partitions(NEW_ORDERS_TOPIC_PARTITIONS)
                        .replicas(3)
                        .build(),
                TopicBuilder.name("payed_orders")
                        .partitions(PAYED_ORDERS_TOPIC_PARTITIONS)
                        .replicas(3)
                        .build(),
                TopicBuilder.name("sent_orders")
                        .partitions(SENT_ORDERS_TOPIC_PARTITIONS)
                        .replicas(3)
                        .build());
    }
}
