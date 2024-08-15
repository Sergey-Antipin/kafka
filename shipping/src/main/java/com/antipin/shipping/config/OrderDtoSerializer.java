package com.antipin.shipping.config;

import com.antipin.OrderDto;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class OrderDtoSerializer implements Serializer<OrderDto> {

    @Override
    public byte[] serialize(String topic, OrderDto data) {
        if (data == null) {
            return null;
        }

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {
            oos.writeObject(data);
            return bos.toByteArray();
        } catch (IOException e) {
            throw new SerializationException("Error serializing OrderDto", e);
        }
    }
}
