package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Order;
import com.inventory.productmanagementsystem.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/order/")
@ResponseBody
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("getOrders")
    public List<Order> getAllOrders() {
        return orderService.getOrders();
    }

    @PostMapping("addOrder")
    public ResponseEntity<String> addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @DeleteMapping(path = "deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable("id") Long orderId) {
        return orderService.deleteOrder(orderId);
    }

    @PutMapping(path = "updateOrder/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable("id") Long orderId, @RequestBody Order order) {
        return orderService.updateOrder(orderId, order);
    }
}
