package com.inventory.productmanagementsystem.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "orders")
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "no_of_items")
    private Integer noOfItems;
    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> productList = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    public Order() {
    }

    public Order(LocalDateTime createdAt, Double totalPrice, Integer noOfItems, List<Product> productList, User userId) {
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.noOfItems = noOfItems;
        this.productList = productList;
        this.userId = userId;
    }

    public Order(LocalDateTime createdAt, Double totalPrice, Integer noOfItems) {
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.noOfItems = noOfItems;
    }

    public Order(Long id, LocalDateTime createdAt, Double totalPrice, Integer noOfItems, List<Product> productList, User userId) {
        this.id = id;
        this.createdAt = createdAt;
        this.totalPrice = totalPrice;
        this.noOfItems = noOfItems;
        this.productList = productList;
        this.userId = userId;
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

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id = " + id +
                ", createdAt = " + createdAt +
                ", totalPrice = " + totalPrice +
                ", noOfItems = " + noOfItems +
                ", productList = " + productList +
                ", userId = " + userId +
                '}';
    }
}
