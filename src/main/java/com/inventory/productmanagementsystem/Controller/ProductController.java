package com.inventory.productmanagementsystem.Controller;

import com.inventory.productmanagementsystem.Model.Product;
import com.inventory.productmanagementsystem.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/product/")
@ResponseBody
public class ProductController {
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("getProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping(path = "deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Long productId) {
        return productService.deleteProduct(productId);
    }

    @PutMapping(path = "updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") Long productId, @RequestBody Product product) {
        return productService.updateProduct(productId, product);
    }
}
