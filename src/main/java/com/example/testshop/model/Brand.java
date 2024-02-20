package com.example.testshop.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue
    private Long id;
    private double averagePrice;
    @Column(name = "brand_name")
    private String name;
    @Column(name = "brand_description")
    private String description;

    //OneToMany relation with the product entity
    @OneToMany(mappedBy = "brand")
    private List<Product> products;
}
