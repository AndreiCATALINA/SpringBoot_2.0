package com.example.testshop.service;

import com.example.testshop.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> readAllBrands();

    Optional<Brand> getBrandById(Long id);

    Brand saveBrand(Brand brand);
    void deleteBrandById(Long id);

    Brand updateBrand(Brand brand);

    List<Brand> getAllBrandsByName(String name);

}
