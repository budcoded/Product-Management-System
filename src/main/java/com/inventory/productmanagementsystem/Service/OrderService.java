package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Exceptions.ResourceNotFoundException;
import com.inventory.productmanagementsystem.Model.Order;
import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Model.User;
import com.inventory.productmanagementsystem.Repository.OrderRepository;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    public OrderService(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public List<Order> getOrders() {
        logger.info("Getting all order's list from database.");
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
        logger.info("Adding an order in database.");
        order.setCreatedAt(LocalDateTime.now());
        Order savedOrder = orderRepository.save(order);
        if (savedOrder.getNoOfItems().equals(order.getNoOfItems())) {
            logger.info("Order added successfully in the database.");
            return ResponseEntity.ok("Order Successfully Added.");
        } else {
            logger.warn("Some error in adding order.");
            return ResponseEntity.status(400).body("Error in Adding Order.");
        }
    }

    public ResponseEntity<String> createOrder (Long userId, List<Product> list) {
        logger.info("User with id: " + userId + " is placing an order.");
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
            logger.info("Order successfully added.");
            return ResponseEntity.ok("Order Successfully Created.");
        } else {
            logger.warn("There was some error while adding the order.");
            return ResponseEntity.status(400).body("Error in Adding Order");
        }
    }

    public ResponseEntity<String> deleteOrder(Long id) {
        logger.info("Deleting an order with id: " + id);
        if (!orderRepository.existsById(id)) {
            logger.warn("Order with id: " + id + " can't be deleted because it doesn't exist in the database.");
            return ResponseEntity.status(400).body("Order with id: " + id + " doesn't exist.");
        } else {
            orderRepository.deleteById(id);
            logger.info("Order with id: " + id + " successfully deleted.");
            return ResponseEntity.status(200).body("Order deleted successfully.");
        }
    }

    @Transactional
    public ResponseEntity<String> updateOrder(Long orderId, Order order) {
        logger.info("Updating an order with id: " + orderId);
        Order updatedOrder = orderRepository.findById(orderId).orElseThrow(() -> new IllegalStateException("Order with id: " + orderId + " doesn't exist."));
        updatedOrder.setNoOfItems(order.getNoOfItems());
        updatedOrder.setTotalPrice(order.getTotalPrice());
        updatedOrder.setCreatedAt(LocalDateTime.now());
        updatedOrder.getProductList().addAll(order.getProductList());
        logger.info("Order with id: " + orderId + " updated successfully.");
        return ResponseEntity.status(200).body("Order updated successfully.");
    }
}
