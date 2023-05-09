package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Exceptions.ResourceNotFoundException;
import com.inventory.productmanagementsystem.Model.Order;
import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Repository.OrderRepository;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getOrders() {
        List<Order> orderList = orderRepository.findAll();
        orderList.forEach(order -> {
            order.getUserId().getOrderList().clear();
            order.getUserId().getComplaintList().clear();
            order.getProductList().forEach(product -> {
                product.getOrderList().clear();
            });
        });
        return orderList;
    }

    public ResponseEntity<String> addOrder(Order order) {
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getNoOfItems().equals(order.getNoOfItems())) {
            return ResponseEntity.ok("Order Successfully Added.");
        } else {
            return ResponseEntity.status(400).body("Error in Adding Order.");
        }
    }

    public ResponseEntity<String> createOrder (Long userId, List<Product> list) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
        Order order = new Order();
        order.setProductList(new ArrayList<>());
        order.setCreatedAt(LocalDateTime.now());
        order.getProductList().addAll(list);
        order.setNoOfItems(order.getProductList().size());
        double price = 0.0;
        for (Product product : list) {
            price += product.getPrice();
        }
        order.setUserId(user);
        order.setTotalPrice(price);
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getNoOfItems().equals(order.getNoOfItems())) {
            return ResponseEntity.ok("Order Successfully Created.");
        } else {
            return ResponseEntity.status(400).body("Error in Adding Order");
        }
    }

    public ResponseEntity<String> deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            return ResponseEntity.status(400).body("Order with id: " + id + " doesn't exist.");
        } else {
            orderRepository.deleteById(id);
            return ResponseEntity.status(200).body("Order deleted successfully.");
        }
    }

    @Transactional
    public ResponseEntity<String> updateOrder(Long orderId, Order order) {
        Order updatedOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with id: " + orderId + " doesn't exist."));
        updatedOrder.setNoOfItems(order.getNoOfItems());
        updatedOrder.setTotalPrice(order.getTotalPrice());
        updatedOrder.setCreatedAt(LocalDateTime.now());
        updatedOrder.getProductList().addAll(order.getProductList());
        return ResponseEntity.status(200).body("Order updated successfully.");
    }
}
