package com.crmax.persistence.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "products_table")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotEmpty(message = "Product Name is required")
    @Size(max = 30, message = "Product Name is too long")
    @Column(name = "product_name")
    private String productName;

    @NotEmpty(message = "Product Code is required")
    @Size(max = 30, message = "Product Name is too long")
    @Column(name = "product_code")
    private String productCode;

    @Positive(message = "Price must be greater than 0")
    @Column(name = "price")
    private Double price;

    @Column(name = "active")
    private Boolean active;

    @ManyToMany(mappedBy = "products")
    private List<Interaction> interactions = new ArrayList<>();

    public Product() {
    }

    public Product(String productName, String productCode, Double price) {
        this.productName = productName;
        this.productCode = productCode;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productCode='" + productCode + '\'' +
                ", active='" + active + '\'' +
                ", price=" + price +
                '}';
    }
}
