package com.inventory.productmanagementsystem.Service;

import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
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
        Product savedProduct = productRepository.save(product);
        if (savedProduct.getName().equals(product.getName())) {
            return ResponseEntity.ok("Product Successfully Added.");
        } else {
            return ResponseEntity.status(400).body("Error in Adding Product.");
        }
    }

    public ResponseEntity<String> deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.status(400).body("Product with id: " + id + " doesn't exist.");
        } else {
            productRepository.deleteById(id);
            return ResponseEntity.status(200).body("Product deleted successfully.");
        }
    }

    @Transactional
    public ResponseEntity<String> updateProduct(Long productId, Product product) {
        Product updatedProduct = productRepository.findById(productId).orElseThrow(() -> new IllegalStateException("Product with id: " + productId + " doesn't exist."));
        updatedProduct.setName(product.getName());
        updatedProduct.setDescription(product.getDescription());
        updatedProduct.setPrice(product.getPrice());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setProductCategory(product.getProductCategory());
        updatedProduct.getOrderList().addAll(product.getOrderList());
        return ResponseEntity.status(200).body("Product updated successfully.");
    }
}
