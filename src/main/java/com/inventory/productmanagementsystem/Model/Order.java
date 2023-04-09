package com.inventory.productmanagementsystem.Model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private Long id;
    private LocalDateTime createdAt;
    private Double totalPrice;
    private Integer noOfItems;
    private List<Product> productList;
    private User customer;

    public Order() {
    }

    public Order(LocalDateTime createdAt, Double totalPrice, Integer noOfItems, List<Product> productList, User customer) {
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.noOfItems = noOfItems;
        this.productList = productList;
        this.customer = customer;
    }

    public Order(Long id, LocalDateTime createdAt, Double totalPrice, Integer noOfItems, List<Product> productList, User customer) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.noOfItems = noOfItems;
        this.productList = productList;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getNoOfItems() {
        return noOfItems;
    }

    public void setNoOfItems(Integer noOfItems) {
        this.noOfItems = noOfItems;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", totalPrice=" + totalPrice +
                ", noOfItems=" + noOfItems +
                ", productList=" + productList +
                ", customer=" + customer +
                '}';
    }
}
