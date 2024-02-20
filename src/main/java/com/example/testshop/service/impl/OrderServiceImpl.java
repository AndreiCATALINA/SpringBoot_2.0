package com.example.testshop.service.impl;

import com.example.testshop.model.Order;
import com.example.testshop.repository.OrderRepository;
import com.example.testshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> readAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrderById(Long id) {
     orderRepository.deleteById(id);
    }

    @Override
    public List<Order> getAllOrdersByName(String name) {
        return orderRepository.getOrdersByName(name);
    }
}
