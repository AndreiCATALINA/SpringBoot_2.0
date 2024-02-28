package com.example.testshop.dto;

import com.example.testshop.model.Order;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class OrderDTOMapper implements Function<Order,OrderDTO> {
    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getId(),
                order.getName(),
                order.getTotal(),
                order.getQuantity()
        );
    }
}
