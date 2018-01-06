package com.crmax.persistence.service;

import com.crmax.persistence.dao.ProductDao;
import com.crmax.persistence.model.Contact;
import com.crmax.persistence.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
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
    public List<Product> findAllActiveProducts() {
        return productDao.findByActiveIsTrue();
    }

    @Override
    public Boolean isDuplicate(Product product) {
        return productDao.findByProductCode(product.getProductCode()).size() > 0;
    }

    @Override
    public List<Product> findAllByIds(String ids) {
        List<String> idsList = new ArrayList<>(Arrays.asList(ids.split(",")));
        List<Integer> integerIds = new ArrayList<>();

        for(String id : idsList){
            integerIds.add(Integer.valueOf(id));
        }

        return productDao.findByIdIsIn(integerIds);
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(Integer.valueOf(id)).get();
    }

    @Override
    public void removeProduct(Product product) {
        product.setActive(false);
        productDao.save(product);
    }

    private String resolveInsertionStatus(Product persistedProduct){

        if(persistedProduct != null)
            return ProductService.InsertionStatus.SUCCESS.name();
        else
            return ProductService.InsertionStatus.ERROR.name();

    }
}
