package com.inventory.productmanagementsystem.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "order")
@Table(name = "order")
public class Order {
    @Id
    @SequenceGenerator(name = "order_sequence", sequenceName =  "order_sequence" , allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "total_price")
    private Double totalPrice;
    @Column(name = "no_of_items")
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
