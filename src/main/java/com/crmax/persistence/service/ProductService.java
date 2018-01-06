package com.crmax.persistence.service;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Product;

import java.util.List;

public interface ProductService {
    String save(Product product);
    List<Product> findAllProducts();
    List<Product> findAllActiveProducts();
    Boolean isDuplicate(Product product);
    List<Product> findAllByIds(String ids);
    Product findById(String id);
    void removeProduct(Product product);

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }
}
