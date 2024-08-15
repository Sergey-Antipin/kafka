package com.antipin.orders.config;

import com.antipin.OrderDto;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.kafka.support.serializer.DeserializationException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class OrderDtoDeserializer implements Deserializer<OrderDto> {

    @Override
    public OrderDto deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInputStream ois = new ObjectInputStream(bis)) {
            return (OrderDto) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new DeserializationException("Error deserializing OrderDto", data, false, e);
        }
    }
}
