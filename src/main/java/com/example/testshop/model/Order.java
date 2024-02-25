package com.example.testshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "order_name")
    private String name;
    @Column(name = "order_total")
    private double total;
    @Column(name = "order_quantity")
    private int quantity;

    //ManyToOne relation with the client entity
    @ManyToOne
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    @JsonIgnoreProperties("orders")
    private Client client;

    //ManyToMany relation with the product entity
    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "id"))
    @JsonIgnoreProperties("orders")
    private List<Product> products;
}
