package com.example.testshop.service.impl;

import com.example.testshop.dto.ProductDTO;
import com.example.testshop.dto.ProductDTOMapper;
import com.example.testshop.model.Product;
import com.example.testshop.repository.ProductRepository;
import com.example.testshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductDTOMapper productDTOMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductDTOMapper productDTOMapper) {
        this.productRepository = productRepository;
        this.productDTOMapper = productDTOMapper;
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productDTOMapper)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> findProductById(Long id) {
        return productRepository.findById(id).map(productDTOMapper);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> getAllProductsByName(String name) {
        return productRepository.getProductsByName(name);
    }
}
