package com.example.testshop.service;

import com.example.testshop.dto.ProductDTO;
import com.example.testshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<ProductDTO> findAllProducts();
    Optional<ProductDTO> findProductById(Long id);
    Product saveProduct(Product product);
    Product updateProduct(Product product);
    void deleteProductById(Long id);
    List<Product> getAllProductsByName(String name);
}
