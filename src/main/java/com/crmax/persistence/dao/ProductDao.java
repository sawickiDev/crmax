package com.crmax.persistence.dao;

import com.crmax.persistence.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao extends CrudRepository<Product, Integer> {
    List<Product> findAll();
    List<Product> findByProductCode(String productCode);
    List<Product> findByIdIsIn(List<Integer> Id);
}
