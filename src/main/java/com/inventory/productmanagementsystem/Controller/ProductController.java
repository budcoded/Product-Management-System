package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product/")
@ResponseBody
public class ProductController {
    private final ProductService productService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getProductById/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") Long id) {
        logger.info("Getting product information by product id: " + id);
        return productService.getProductById(id);
    }
    @GetMapping("getProducts")
    public ResponseEntity<?> getAllProducts() {
        logger.info("Getting all products.");
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("addProduct")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        logger.info("Admin is adding a product named: " + product.getName());
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        logger.warn("User is deleting a product with product id: " + productId);
        return productService.deleteProduct(productId);
    }

    @PutMapping(path = "updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        logger.info("Updating a product with product id: " + productId);
        return productService.updateProduct(productId, product);
    }
}
