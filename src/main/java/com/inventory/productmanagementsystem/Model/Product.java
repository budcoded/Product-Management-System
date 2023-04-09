package com.inventory.productmanagementsystem.Model;

import java.util.List;

public class Product {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private Long quantity;
    private List<Order> orderList;
    private ProductCategory productCategory;


    public Product() {
    }

    public Product(String name, String description, Double price, Long quantity, List<Order> orderList, ProductCategory productCategory) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.orderList = orderList;
        this.productCategory = productCategory;
    }

    public Product(Long id, String name, String description, Double price, Long quantity, List<Order> orderList, ProductCategory productCategory) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.orderList = orderList;
        this.productCategory = productCategory;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
}
