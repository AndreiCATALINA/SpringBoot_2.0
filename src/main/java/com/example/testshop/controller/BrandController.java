package com.example.testshop.controller;

import com.example.testshop.exception.ResourceNotFoundException;
import com.example.testshop.model.Brand;
import com.example.testshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private final BrandService brandService;

    @GetMapping //http://localhost:8181/api/brand
    public ResponseEntity<List<Brand>> getAllBrands() {
        List<Brand> brands = brandService.readAllBrands();
        if (brands.isEmpty()) {
            throw new ResourceNotFoundException("There are no brands in DB");
        }
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/brandById/{id}") //http://localhost:8181/api/brand/brandById/{id}
    public ResponseEntity<Optional<Brand>> getBrandById(@PathVariable Long id) {
        Optional<Brand> brandOptional = brandService.getBrandById(id);
        brandOptional.orElseThrow(() ->
                new ResourceNotFoundException("The brand with id: " + id + " doesn't exist in DB"));
        return new ResponseEntity<>(brandOptional, HttpStatus.OK);
    }

    @GetMapping("/brandsByName/{name}") //http://localhost:8181/api/brand/brandsByName/{name}
    public ResponseEntity<List<Brand>> getAllBrandsByName(@PathVariable String name) {
        List<Brand> brandList = brandService.getAllBrandsByName(name);
        if (brandList.isEmpty()) {
            throw new ResourceNotFoundException("The brand with name: " + name + " doesn't exist in DB");
        }
        return new ResponseEntity<>(brandList, HttpStatus.OK);
    }

    @PostMapping("/saveNewBrand") //http://localhost:8181/api/brand/saveNewBrand
    public ResponseEntity<Brand> saveBrand(@RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.saveBrand(brand), HttpStatus.OK);
    }

    @PutMapping("/updateBrand") //http://localhost:8181/api/brand/updateBrand
    public ResponseEntity<Brand> updateBrand(@RequestBody Brand brand) {
        return new ResponseEntity<>(brandService.updateBrand(brand), HttpStatus.OK);
    }

    @DeleteMapping("/deleteBrandById/{id}") //http://localhost:8181/api/brand/deleteBrandById/{id}
    public ResponseEntity<?> deleteBrandById(@PathVariable Long id) {
        Optional<Brand> brandOptional = brandService.getBrandById(id);
        brandOptional.orElseThrow(() ->
                new ResourceNotFoundException("The brand with id: " + id + " doesn't exist in DB"));
        brandService.deleteBrandById(id);
        return new ResponseEntity<>("The brand with the id: " + id + "  was deleted successfully", HttpStatus.OK);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
