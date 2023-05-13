package com.inventory.productmanagementsystem;

import com.inventory.productmanagementsystem.Model.*;
import com.inventory.productmanagementsystem.Repository.OrderRepository;
import com.inventory.productmanagementsystem.Repository.ProductRepository;
import com.inventory.productmanagementsystem.Repository.UserRepository;
import com.inventory.productmanagementsystem.Service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class OrderTests {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(OrderTests.this);
        orderService = new OrderService(orderRepository, userRepository);
    }

//    @Test
//    void testGetOrders() {
//        // Create some test orders
//        Order order1 = new Order();
//        Order order2 = new Order();
//        Order order3 = new Order();
//        // Add orders to repository
//        orderRepository.saveAll(List.of(order1, order2, order3));
//        // Add some products to the orders
//        Product product1 = new Product("Product 1", "Description 1", 10.0, 1L, ProductCategory.GROCERIES);
//        Product product2 = new Product("Product 2", "Description 2", 20.0, 2L, ProductCategory.HOMEANDKITCHEN);
//        order1.getProductList().add(product1);
//        order1.getProductList().add(product2);
//        // Add user to order
//        User user = new User();
//        user.setEmail("user@email.com");
//        user.setPassword("password");
//        user.getOrderList().add(order1);
//        user.getOrderList().add(order2);
//        order1.setUserId(user);
//        order2.setUserId(user);
//        order3.setUserId(user);
//        // Save products and user to the repository
//        productRepository.saveAll(List.of(product1, product2));
//        userRepository.save(user);
//        // Call the getOrders method
//        List<Order> orderList = orderService.getOrders();
//        // Assert that the returned list contains the same orders that were added to the repository
//        assertEquals(List.of(order1, order2, order3), orderList);
//        // Assert that each order in the list has its product list and user cleared
//        for (Order order : orderList) {
//            assertTrue(order.getProductList().isEmpty());
//            assertNull(order.getUserId());
//        }
//    }

//    @Test
//    void testAddOrder() {
//        // Create a new order
//        Order order = new Order();
//        order.setTotalPrice(100.0);
//        order.setNoOfItems(2);
//        // Add some products to the order
//        List<Product> products = new ArrayList<>();
//        Product product1 = new Product();
//        product1.setName("Product 1");
//        product1.setPrice(50.0);
//        product1.setQuantity(1L);
//        products.add(product1);
//        Product product2 = new Product();
//        product2.setName("Product 2");
//        product2.setPrice(50.0);
//        product2.setQuantity(1L);
//        products.add(product2);
//        order.setProductList(products);
//        // Call the addOrder method
//        ResponseEntity<String> response = orderService.addOrder(order);
//        // Verify that the order was added successfully
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Order Successfully Added.", response.getBody());
//        // Verify that the order was saved in the database
//        List<Order> orderList = orderService.getOrders();
//        assertTrue(orderList.contains(order));
//    }

//    @Test
//    public void testCreateOrder() {
//        Long userId = 1L;
//        List<Product> productList = new ArrayList<>();
//        Product product1 = new Product();
//        product1.setPrice(10.0);
//        productList.add(product1);
//        Product product2 = new Product();
//        product2.setPrice(20.0);
//        productList.add(product2);
//        User user = new User();
//        user.setId(userId);
//        Order order = new Order();
//        order.setUserId(user);
//        order.setProductList(productList);
//        order.setNoOfItems(productList.size());
//        order.setTotalPrice(30.0);
//        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        when(orderRepository.save(any(Order.class))).thenReturn(order);
//        ResponseEntity<String> responseEntity = orderService.createOrder(userId, productList);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals("Order Successfully Created.", responseEntity.getBody());
//        verify(userRepository, times(1)).findById(userId);
//        verify(orderRepository, times(1)).save(any(Order.class));
//    }

//    @Test
//    public void testDeleteOrder() {
//        // create an order to be deleted
//        Order order = new Order();
//        order.setProductList(new ArrayList<>());
//        order.setCreatedAt(LocalDateTime.now());
//        order.setNoOfItems(2);
//        order.setTotalPrice(100.0);
//        User user = new User();
//        user.setName("testuser");
//        user.setPassword("testpassword");
//        user.setEmail("testuser@example.com");
//        user.setRole(UserRole.CUSTOMER);
//        user = userRepository.save(user);
//        order.setUserId(user);
//        order = orderRepository.save(order);
//        // call the deleteOrder method
//        ResponseEntity<String> response = orderService.deleteOrder(order.getId());
//        // verify that the order was deleted
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Order deleted successfully.", response.getBody());
//        assertFalse(orderRepository.existsById(order.getId()));
//    }

//    @Test
//    public void testUpdateOrder() {
//        // Create a user
//        User user = new User();
//        user.setName("testuser");
//        user.setEmail("testuser@example.com");
//        user.setPassword("testpassword");
//        User savedUser = userRepository.save(user);
//        // Create an order
//        List<Product> productList = new ArrayList<>();
//        Product product1 = new Product();
//        product1.setName("Product 1");
//        product1.setPrice(10.0);
//        Product savedProduct1 = productRepository.save(product1);
//        productList.add(savedProduct1);
//        Order order = new Order();
//        order.setUserId(savedUser);
//        order.setProductList(productList);
//        order.setNoOfItems(productList.size());
//        order.setTotalPrice(productList.stream().mapToDouble(Product::getPrice).sum());
//        Order savedOrder = orderRepository.save(order);
//        // Update the order
//        Order updatedOrder = new Order();
//        updatedOrder.setNoOfItems(2);
//        updatedOrder.setTotalPrice(20.0);
//        List<Product> updatedProductList = new ArrayList<>();
//        Product product2 = new Product();
//        product2.setName("Product 2");
//        product2.setPrice(15.0);
//        Product savedProduct2 = productRepository.save(product2);
//        updatedProductList.add(savedProduct2);
//        updatedOrder.setProductList(updatedProductList);
//        ResponseEntity<String> response = orderService.updateOrder(savedOrder.getId(), updatedOrder);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        Order retrievedOrder = orderRepository.findById(savedOrder.getId()).orElse(null);
//        assertNotNull(retrievedOrder);
//        assertEquals(updatedOrder.getNoOfItems(), retrievedOrder.getNoOfItems());
//        assertEquals(updatedOrder.getTotalPrice(), retrievedOrder.getTotalPrice());
//        assertEquals(updatedOrder.getProductList().size(), retrievedOrder.getProductList().size());
//        assertTrue(retrievedOrder.getProductList().contains(savedProduct1));
//        assertTrue(retrievedOrder.getProductList().contains(savedProduct2));
//    }
}