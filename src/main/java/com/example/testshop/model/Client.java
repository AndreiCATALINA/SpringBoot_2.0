package com.example.testshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name ="clients")
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "client_name")
    private String name;
    private String email;
    private String phone;
    private String address;

    //OneToMany relation with order entity
    @OneToMany(mappedBy = "client")
    @JsonIgnoreProperties("client")
    private List<Order> orders;
}
