package com.Ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.Ecommerce.Entity.Category;
import com.Ecommerce.Entity.Product;
import com.Ecommerce.Repository.CategoryRepository;
import com.Ecommerce.Repository.ProductRepository;

import java.util.Optional;

@Service
public class ProductService 
{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all products with pagination
    public Page<Product> getAllProducts(int page, int size) 
    {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    // Get product by ID
    public Product getProductById(Long id)
    {
        return productRepository.findById(id).orElse(null);
    }

    // Create a new product
    public Product createProduct(Product product, Long categoryId) 
    {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent())
        {
            product.setCategory(category.get());
            return productRepository.save(product);
        }
        return null;
    }

    // Update product
    public Product updateProduct(Long id, Product updatedProduct) 
    {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) 
        {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            return productRepository.save(existingProduct);
        }
        return null;
    }

    // Delete product
    public void deleteProduct(Long id) 
    {
        productRepository.deleteById(id);
    }
}