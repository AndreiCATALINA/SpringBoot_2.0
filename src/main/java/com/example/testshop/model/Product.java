package com.example.testshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "product_name")
    private String name;
    @Column(nullable = false,name = "product_quantity")
    private int quantity;
    @Column(name = "product_validity",nullable = false)
    private Boolean validity;

    //ManyToMany relation with the order entity
    @ManyToMany(mappedBy = "products" ,fetch = FetchType.LAZY)
    @JsonIgnoreProperties("products")
    private List<Order> orders;

    //ManyToOne relation with the brand entity
    @ManyToOne
    @JoinColumn(name = "brand_id",referencedColumnName = "id")
    @JsonIgnoreProperties("products")
    private Brand brand;
}
