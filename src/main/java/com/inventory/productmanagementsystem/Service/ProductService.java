package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        logger.info("Getting all the products.");
        List<Product> productList = productRepository.findAll();
        productList.forEach(product -> {
            product.getOrderList().forEach(order -> {
                order.getProductList().clear();
                order.setUserId(null);
            });
        });
        return productList;
    }

    public ResponseEntity<String> addProduct(Product product) {
        logger.info("Adding a product with name: " + product.getName());
        Product savedProduct = productRepository.save(product);
        if (savedProduct.getName().equals(product.getName())) {
            logger.info("Product added successfully.");
            return ResponseEntity.ok("Product Successfully Added.");
        } else {
            logger.info("There was an error adding the product.");
            return ResponseEntity.status(400).body("Error in Adding Product.");
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        logger.info("Trying to delete a product with id: " + id);
        if (!productRepository.existsById(id)) {
            logger.warn("Product with id: " + id + " doesn't exist.");
            return ResponseEntity.status(400).body("Product with id: " + id + " doesn't exist.");
        } else {
            productRepository.deleteById(id);
            logger.info("Product with id: " + id + " deleted successfully.");
            return ResponseEntity.status(200).body("Product deleted successfully.");
        }
    }

    @Transactional
    public ResponseEntity<String> updateProduct(Long productId, Product product) {
        logger.info("Finding the product with product id: " + productId);
        Product updatedProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id: " + productId + " doesn't exist."));
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setProductCategory(product.getProductCategory());
        updatedProduct.getOrderList().addAll(product.getOrderList());
        logger.info("Saving updated product into database.");
        return ResponseEntity.status(200).body("Product updated successfully.");
    }

    public ResponseEntity<Object> getProductById(Long id) {
        logger.info("Finding product by product id: " + id);
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.getOrderList().forEach(order -> {
                order.getProductList().clear();
                order.setUserId(null);
            });
            logger.info("Product is found with product id: " + id);
            return ResponseEntity.ok(product);
        } else {
            logger.info("Product with product id: " + id + " doesn't exist.");
            return ResponseEntity.status(400).body("Product Not Found");
        }
    }
}
