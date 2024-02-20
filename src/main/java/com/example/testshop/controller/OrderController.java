package com.example.testshop.controller;

import com.example.testshop.exception.ResourceNotFoundException;
import com.example.testshop.model.Order;
import com.example.testshop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping //http://localhost:8181/api/order
    public ResponseEntity<List<Order>> getAllOrders() {
        return new ResponseEntity<>(orderService.readAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/orderById/{id}") //http://localhost:8181/api/order/orderById/{id}
    public ResponseEntity<Optional<Order>> getOrderById(@PathVariable Long id) {
        Optional<Order> orderOptional = orderService.getOrderById(id);
        orderOptional.orElseThrow(() ->
                new ResourceNotFoundException("The order with id " + id + " doesn't exist in DB"));
        return new ResponseEntity<>(orderOptional, HttpStatus.OK);
    }

    @GetMapping("/ordersByName/{name}") //http://localhost:8181/api/order/ordersByName/{name}
    public ResponseEntity<List<Order>> getAllOrdersByName(@PathVariable String name) {
        List<Order> orderList = orderService.getAllOrdersByName(name);
        if (orderList.isEmpty()) {
            throw new ResourceNotFoundException("The order with name " + name + " doesn't exist in DB");
        }
        return new ResponseEntity<>(orderList, HttpStatus.OK);
    }

    @PostMapping("/saveNewOrder") //http://localhost:8181/api/order/saveNewOrder
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.saveOrder(order), HttpStatus.OK);
    }

    @PutMapping("/updateOrder") //http://localhost:8181/api/order/updateOrder
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(order),HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrderById/{id}") //http://localhost:8181/api/order/deleteOrderById/{id}
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id){
        Optional<Order> orderOptional = orderService.getOrderById(id);
        orderOptional.orElseThrow(()->
                new ResourceNotFoundException("The order with id " + id + " doesn't exist in DB"));
        orderService.deleteOrderById(id);
        return new ResponseEntity<>("The order with id " + id + " was deleted",HttpStatus.OK);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
