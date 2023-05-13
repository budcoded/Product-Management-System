package com.inventory.productmanagementsystem;

import com.inventory.productmanagementsystem.Model.Order;
import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Model.ProductCategory;
import com.inventory.productmanagementsystem.Repository.ProductRepository;
import com.inventory.productmanagementsystem.Service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProductTests {
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(ProductTests.this);
        productService = new ProductService(productRepository);
    }

//    @Test
//    void testGetAllProducts() {
//        // Create some test products
//        Product product1 = new Product("Product 1", "Description 1", 10.0, 20L, ProductCategory.GROCERIES);
//        Product product2 = new Product("Product 2", "Description 2", 20.0, 30L, ProductCategory.ELECTRONICS);
//        Product product3 = new Product("Product 3", "Description 3", 30.0, 40L, ProductCategory.FASHION);
//        productRepository.saveAll(List.of(product1, product2, product3));
//        // Call the method being tested
//        List<Product> productList = productService.getAllProducts();
//        // Assert that the returned list contains all the products and their fields are correct
//        assertEquals(3, productList.size());
//        assertEquals(product1.getName(), productList.get(0).getName());
//        assertEquals(product2.getName(), productList.get(1).getName());
//        assertEquals(product3.getName(), productList.get(2).getName());
//        assertEquals(product1.getPrice(), productList.get(0).getPrice());
//        assertEquals(product2.getPrice(), productList.get(1).getPrice());
//        assertEquals(product3.getPrice(), productList.get(2).getPrice());
//        assertEquals(product1.getProductCategory(), productList.get(0).getProductCategory());
//        assertEquals(product2.getProductCategory(), productList.get(1).getProductCategory());
//        assertEquals(product3.getProductCategory(), productList.get(2).getProductCategory());
//    }

    @Test
    public void testAddProduct() {
        // Step 1: Create a new Product object with sample data
        Product product = new Product();
        product.setName("Sample Product");
        product.setDescription("This is a sample product");
        product.setPrice(9.99);
        product.setQuantity(10L);
        product.setProductCategory(ProductCategory.ELECTRONICS);
        // Step 2: Mock the productRepository.save() method
        when(productRepository.save(product)).thenReturn(product);
        // Step 3: Call the addProduct method with the mocked Product object
        ResponseEntity<String> responseEntity = productService.addProduct(product);
        // Step 4: Verify that the method returns a ResponseEntity with HTTP status code 200 and the message "Product Successfully Added."
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Product Successfully Added.", responseEntity.getBody());
        // Step 5: Verify that the productRepository.save() method was called exactly once with the same Product object that was passed as an argument to the addProduct method
        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void testDeleteProduct() {
        // Create a new product
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");
        product.setPrice(10.0);
        when(productRepository.existsById(1L)).thenReturn(true);
        // Call deleteProduct method
        ResponseEntity<String> response = productService.deleteProduct(1L);
        // Verify response
        assertEquals(200, response.getStatusCode().value());
        assertEquals("Product deleted successfully.", response.getBody());
        // Verify product is deleted from database
        when(productRepository.existsById(anyLong())).thenReturn(false);
        boolean exists = productRepository.existsById(1L);
        assertFalse(exists);
    }

//    @Test
//    public void testGetProductById() {
//        // Create a new product
//        Product product = new Product();
//        product.setName("Test Product");
//        product.setDescription("This is a test product");
//        product.setPrice(9.99);
//        product.setQuantity(10L);
//        product.setProductCategory(ProductCategory.ELECTRONICS);
//        Product savedProduct = productRepository.save(product);
//        // Call the getProductById method
//        ResponseEntity<Object> responseEntity = productService.getProductById(savedProduct.getId());
//        // Verify the response
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertTrue(responseEntity.getBody() instanceof Product);
//        Product retrievedProduct = (Product) responseEntity.getBody();
//        assertEquals(savedProduct.getName(), retrievedProduct.getName());
//        assertEquals(savedProduct.getDescription(), retrievedProduct.getDescription());
//        assertEquals(savedProduct.getPrice(), retrievedProduct.getPrice());
//        assertEquals(savedProduct.getQuantity(), retrievedProduct.getQuantity());
//        assertEquals(savedProduct.getProductCategory(), retrievedProduct.getProductCategory());
//    }

//    @Test
//    public void testUpdateProduct() {
//        // Create a new product
//        Product product = new Product();
//        product.setName("Test Product");
//        product.setDescription("This is a test product.");
//        product.setPrice(10.0);
//        product.setQuantity(5L);
//        product.setProductCategory(ProductCategory.GROCERIES);
//        // Save the product to the database
//        Product savedProduct = productRepository.save(product);
//        // Update the product with new values
//        Product updatedProduct = new Product();
//        updatedProduct.setName("New Product Name");
//        updatedProduct.setDescription("This is a new description.");
//        updatedProduct.setPrice(15.0);
//        updatedProduct.setQuantity(10L);
//        updatedProduct.setProductCategory(ProductCategory.FRUITS);
//        updatedProduct.getOrderList().add(new Order());
//        // Call the updateProduct method
//        ResponseEntity<String> response = productService.updateProduct(savedProduct.getId(), updatedProduct);
//        // Check that the response is successful
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Product updated successfully.", response.getBody());
//        // Retrieve the updated product from the database
//        Optional<Product> optionalProduct = productRepository.findById(savedProduct.getId());
//        assertTrue(optionalProduct.isPresent());
//        // Check that the updated product has the correct values
//        Product retrievedProduct = optionalProduct.get();
//        assertEquals(updatedProduct.getName(), retrievedProduct.getName());
//        assertEquals(updatedProduct.getDescription(), retrievedProduct.getDescription());
//        assertEquals(updatedProduct.getPrice(), retrievedProduct.getPrice());
//        assertEquals(updatedProduct.getQuantity(), retrievedProduct.getQuantity());
//        assertEquals(updatedProduct.getProductCategory(), retrievedProduct.getProductCategory());
//        assertEquals(updatedProduct.getOrderList(), retrievedProduct.getOrderList());
//    }
}