package com.example.testshop.dto;

import com.example.testshop.model.Brand;
import org.springframework.stereotype.Service;

import java.util.function.Function;
@Service
public class BrandDTOMapper implements Function<Brand,BrandDTO> {
    @Override
    public BrandDTO apply(Brand brand) {
        return new BrandDTO(
                brand.getId(),
                brand.getName(),
                brand.getDescription()
        );
    }
}
