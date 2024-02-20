package com.example.testshop.service.impl;

import com.example.testshop.model.Brand;
import com.example.testshop.repository.BrandRepository;
import com.example.testshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> readAllBrands() {
        return brandRepository.findAll();
    }

    @Override
    public Optional<Brand> getBrandById(Long id) {
        return brandRepository.findById(id);
    }

    @Override
    public Brand saveBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrandById(Long id) {
     brandRepository.deleteById(id);
    }

    @Override
    public Brand updateBrand(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public List<Brand> getAllBrandsByName(String name) {
        return brandRepository.getBrandsByName(name);
    }
}
