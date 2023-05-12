package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Order;
import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Service.OrderService;
import com.inventory.productmanagementsystem.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/order/")
@ResponseBody
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService, ProductService productService) {
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("getOrders")
    public List<Order> getAllOrders() {
        logger.info("Getting all the orders.");
        return orderService.getOrders();
    }

    @PostMapping("addOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        logger.info("Adding a order.");
        return orderService.addOrder(order);
    }

    @PostMapping("createOrder/{userId}")
    public ResponseEntity<String> createOrder(@PathVariable("userId") Long userId, @RequestBody List<Product> list) {
        Map<Long, Integer> map = new HashMap<>();
        Map<Long, Product> productMap = new HashMap<>();
        for (Product product : list) {
            if (map.containsKey(product.getId())) {
                map.put(product.getId(), map.get(product.getId()) + 1);
            } else {
                map.put(product.getId(), 1);
                productMap.put(product.getId(), product);
            }
        }
        for (Map.Entry<Long, Integer> entry : map.entrySet()) {
            Long productId = entry.getKey();
            int quantity = entry.getValue();
            Product product = productMap.get(productId);
            product.setQuantity(product.getQuantity() - quantity);
            productService.updateProduct(product.getId(), product);
        }
        logger.info("Customer with user id: " + userId + " is placing an order.");
        return orderService.createOrder(userId, list);
    }

    @DeleteMapping(path = "deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        logger.warn("Deleting an order with order id: " + orderId);
        return orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "updateOrder/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Long orderId, @RequestBody Order order) {
        logger.info("Updating an order with order id: " + orderId);
        return orderService.updateOrder(orderId, order);
    }
}
