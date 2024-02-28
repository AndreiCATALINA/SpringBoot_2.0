package com.example.testshop.service;

import com.example.testshop.dto.OrderDTO;
import com.example.testshop.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<OrderDTO> readAllOrders();
    Optional<OrderDTO> getOrderById(Long id);
    Order saveOrder(Order order);
    Order updateOrder(Order order);
    void deleteOrderById(Long id);
    List<Order> getAllOrdersByName(String name);

}
