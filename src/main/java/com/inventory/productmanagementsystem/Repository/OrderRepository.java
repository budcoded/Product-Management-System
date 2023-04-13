package com.inventory.productmanagementsystem.Repository;

import com.inventory.productmanagementsystem.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
