package com.example.testshop.service.impl;

import com.example.testshop.dto.BrandDTO;
import com.example.testshop.dto.BrandDTOMapper;
import com.example.testshop.model.Brand;
import com.example.testshop.repository.BrandRepository;
import com.example.testshop.service.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;
    private final BrandDTOMapper brandDTOMapper;

    public BrandServiceImpl(BrandRepository brandRepository, BrandDTOMapper brandDTOMapper) {
        this.brandRepository = brandRepository;
        this.brandDTOMapper = brandDTOMapper;
    }

    @Override
    public List<BrandDTO> readAllBrands() {
        return brandRepository.findAll()
                .stream().map(brandDTOMapper)
                .collect(Collectors.toList());
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
