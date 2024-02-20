package com.example.testshop.repository;

import com.example.testshop.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {

    List<Brand> getBrandsByName(String name);


}
