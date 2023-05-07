package com.inventory.productmanagementsystem.Utils;

import com.inventory.productmanagementsystem.Model.*;
import com.inventory.productmanagementsystem.Repository.OrderRepository;
import com.inventory.productmanagementsystem.Repository.ProductRepository;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class DataInitializer {
    /*@Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        return args -> {
            // Creating admin user
            User admin = new User();
            admin.setName("Admin");
            admin.setEmail("Admin@iiitb.ac.in");
            admin.setPassword("password");
            admin.setAddress("Bangalore");
            admin.setRole(UserRole.ADMIN);
            admin.setMobileNumber(8887774441L);

            // Creating customer user
            User customer = new User();
            customer.setName("Customer");
            customer.setEmail("Customer@iiitb.ac.in");
            customer.setPassword("password");
            customer.setAddress("Bangalore");
            customer.setRole(UserRole.CUSTOMER);
            customer.setMobileNumber(6665558882L);

            // Creating complaints for customer
            Complaint firstComplaint = new Complaint();
            firstComplaint.setContent("This is the content of first complaint");
            firstComplaint.setCreatedAt(LocalDateTime.now());

            Complaint secondComplaint = new Complaint();
            secondComplaint.setContent("This is the content of second complaint");
            secondComplaint.setCreatedAt(LocalDateTime.now());

            // Connecting customer user and complaints
            firstComplaint.setUser(customer);
            secondComplaint.setUser(customer);
            customer.getComplaintList().add(firstComplaint);
            customer.getComplaintList().add(secondComplaint);

            // Creating Products
            Product groceryProduct = new Product();
            groceryProduct.setName("Tata Salt");
            groceryProduct.setDescription("1Kg pack of tata salt");
            groceryProduct.setQuantity(50L);
            groceryProduct.setPrice(18.00);
            groceryProduct.setProductCategory(ProductCategory.GROCERIES);

            Product fruitProduct = new Product();
            fruitProduct.setName("Banana");
            fruitProduct.setDescription("1Kg Bananas");
            fruitProduct.setQuantity(100L);
            fruitProduct.setPrice(35.00);
            fruitProduct.setProductCategory(ProductCategory.FRUITS);

            Product homeKitchenProduct = new Product();
            homeKitchenProduct.setName("Pressure Cooker");
            homeKitchenProduct.setDescription("A 5-liter pressure cooker from Prestige");
            homeKitchenProduct.setQuantity(50L);
            homeKitchenProduct.setPrice(849.00);
            homeKitchenProduct.setProductCategory(ProductCategory.HOMEANDKITCHEN);

            Product fashionProduct = new Product();
            fashionProduct.setName("Shoes");
            fashionProduct.setDescription("A pair of men's Adidas running shoes");
            fashionProduct.setQuantity(75L);
            fashionProduct.setPrice(3499.00);
            fashionProduct.setProductCategory(ProductCategory.FASHION);

            Product electronicsProduct = new Product();
            electronicsProduct.setName("Xiaomi Redmi Note 11 Pro");
            electronicsProduct.setDescription("A Xiaomi Redmi Note 11 Pro smartphone");
            electronicsProduct.setQuantity(10L);
            electronicsProduct.setPrice(19999.00);
            electronicsProduct.setProductCategory(ProductCategory.ELECTRONICS);

            Product sportsProduct = new Product();
            sportsProduct.setName("Football");
            sportsProduct.setDescription("A Nivia football");
            sportsProduct.setQuantity(30L);
            sportsProduct.setPrice(399.00);
            sportsProduct.setProductCategory(ProductCategory.SPORTS);

            productRepository.save(groceryProduct);
            productRepository.save(fruitProduct);
            productRepository.save(homeKitchenProduct);
            productRepository.save(fashionProduct);
            productRepository.save(electronicsProduct);
            productRepository.save(sportsProduct);

            Order firstOrder = new Order();
            firstOrder.setCreatedAt(LocalDateTime.now());
//            firstOrder.setTotalPrice(2398.00);
//            firstOrder.setNoOfItems(2);
            firstOrder.getProductList().add(sportsProduct);
            firstOrder.getProductList().add(electronicsProduct);
            sportsProduct.getOrderList().add(firstOrder);
            electronicsProduct.getOrderList().add(firstOrder);
            firstOrder.setNoOfItems(firstOrder.getProductList().size());
            Double price = 0.0;
            for (Product temp : firstOrder.getProductList()) {
                price += temp.getPrice();
            }
            firstOrder.setTotalPrice(price);

            Order secondOrder = new Order();
            secondOrder.setCreatedAt(LocalDateTime.now());
//            secondOrder.setTotalPrice(902.00);
//            secondOrder.setNoOfItems(3);
            secondOrder.getProductList().add(groceryProduct);
            secondOrder.getProductList().add(fruitProduct);
            secondOrder.getProductList().add(homeKitchenProduct);
            groceryProduct.getOrderList().add(secondOrder);
            fruitProduct.getOrderList().add(secondOrder);
            homeKitchenProduct.getOrderList().add(secondOrder);
            secondOrder.setNoOfItems(secondOrder.getProductList().size());
            Double priceSecond = 0.0;
            for (Product temp : secondOrder.getProductList()) {
                priceSecond += temp.getPrice();
            }
            secondOrder.setTotalPrice(priceSecond);

            firstOrder.setUserId(customer);
            secondOrder.setUserId(customer);
            customer.getOrderList().add(firstOrder);
            customer.getOrderList().add(secondOrder);

            // Connecting customer and order
            firstOrder.setUserId(customer);
            secondOrder.setUserId(customer);
            customer.getOrderList().add(firstOrder);
            customer.getOrderList().add(secondOrder);

            userRepository.save(admin);
            userRepository.save(customer);
        };
    }*/
}