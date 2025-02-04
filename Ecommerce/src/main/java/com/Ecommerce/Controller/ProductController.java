package com.Ecommerce.Controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.Ecommerce.Entity.Product;
import com.Ecommerce.Service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController 
{

    @Autowired
    private ProductService productService;

    // Get all products with pagination
    @GetMapping
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size) 
    {
        return productService.getAllProducts(page, size);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) 
    {
        return productService.getProductById(id);
    }

    // Create a new product
    @PostMapping("/{categoryId}")
    public Product createProduct(@RequestBody Product product, @PathVariable Long categoryId) 
    {
        return productService.createProduct(product, categoryId);
    }

    // Update product by ID
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) 
    {
        return productService.updateProduct(id, product);
    }

    // Delete product by ID
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) 
    {
        productService.deleteProduct(id);
    }
}