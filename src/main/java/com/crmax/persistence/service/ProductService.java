package com.crmax.persistence.service;

import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Product;

import java.util.List;

public interface ProductService {
    String save(Product product);
    List<Product> findAllProducts();
    Boolean isDuplicate(Product product);

    public enum InsertionStatus {
        WARNING,
        ERROR,
        SUCCESS
    }
}
