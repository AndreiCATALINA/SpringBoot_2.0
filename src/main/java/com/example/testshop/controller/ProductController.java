package com.example.testshop.controller;

import com.example.testshop.dto.ProductDTO;
import com.example.testshop.exception.ResourceNotFoundException;
import com.example.testshop.model.Product;
import com.example.testshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping //http://localhost:8181/api/product
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.findAllProducts();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("There are no products in DB");
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/productById/{id}") //http://localhost:8181/api/product/productById/{id}
    public ResponseEntity<Optional<ProductDTO>> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> productOptional = productService.findProductById(id);
        productOptional.orElseThrow(() ->
                new ResourceNotFoundException("The product with id " + id + " doesn't exist in DB"));
        return new ResponseEntity<>(productOptional, HttpStatus.OK);
    }

    @GetMapping("/productsByName/{name}") //http://localhost:8181/api/product/productsByName/{name}
    public ResponseEntity<List<Product>> getAllProductsByName(@PathVariable String name) {
        List<Product> productList = productService.getAllProductsByName(name);
        if (productList.isEmpty()) {
            throw new ResourceNotFoundException("The product with name " + name + " doesn't exist in DB");
        }
        return new ResponseEntity<>(productList, HttpStatus.OK);
    }

    @PostMapping("/saveNewProduct") //http://localhost:8181/api/product/saveNewProduct
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.OK);
    }

    @PutMapping("/updateProduct") //http://localhost:8181/api/product/updateProduct
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.updateProduct(product), HttpStatus.OK);
    }

    @DeleteMapping("/deleteProductById/{id}") //http://localhost:8181/api/product/deleteProductById/{id}
    public ResponseEntity<?> deleteProductById(@PathVariable Long id) {
        Optional<ProductDTO> productOptional = productService.findProductById(id);
        productOptional.orElseThrow(() ->
                new ResourceNotFoundException("The product with id " + id + " doesn't exist in DB"));
        productService.deleteProductById(id);
        return new ResponseEntity<>("The product with id " + id + " was deleted", HttpStatus.OK);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlerResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
