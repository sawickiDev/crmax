package com.crmax.persistence.service;

import com.crmax.persistence.dao.ProductDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductDao productDao;

    @Override
    @Transactional
    public String save(Product product) {
        Product persistedProduct;

        System.out.println("Prod : " + product);

        try{
            persistedProduct = productDao.save(product);
        } catch(Exception ex) {
            return ProductService.InsertionStatus.ERROR.name();
        }

        return resolveInsertionStatus(persistedProduct);
    }

    @Override
    public List<Product> findAllProducts() {
        return productDao.findAll();
    }

    @Override
    public Boolean isDuplicate(Product product) {
        return productDao.findByProductCode(product.getProductCode()).size() > 0;
    }

    private String resolveInsertionStatus(Product persistedProduct){

        if(persistedProduct != null)
            return ProductService.InsertionStatus.SUCCESS.name();
        else
            return ProductService.InsertionStatus.ERROR.name();

    }
}
