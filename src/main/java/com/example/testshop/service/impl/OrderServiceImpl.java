package com.example.testshop.service.impl;

import com.example.testshop.dto.OrderDTO;
import com.example.testshop.dto.OrderDTOMapper;
import com.example.testshop.model.Order;
import com.example.testshop.repository.OrderRepository;
import com.example.testshop.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDTOMapper orderDTOMapper) {
        this.orderRepository = orderRepository;
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public List<OrderDTO> readAllOrders() {
        return orderRepository.findAll()
                .stream().map(orderDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<OrderDTO> getOrderById(Long id) {
        return orderRepository.findById(id).map(orderDTOMapper);
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
