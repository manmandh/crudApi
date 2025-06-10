package com.marry.crudapi.service;

import com.marry.crudapi.dto.request.ProductCreationRequest;
import com.marry.crudapi.entity.Category;
import com.marry.crudapi.entity.Product;
import com.marry.crudapi.repository.CategoryRepository;
import com.marry.crudapi.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Product> getAll() {
        return productRepository.findAll();
    }

    public Product getById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> getByCategoryId(Integer categoryId) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .toList();
    }
    private void mapRequestToProduct(ProductCreationRequest request, Product product) {
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("This category does not exist")));

        if (request.getCategoryId() == null) {
            throw new IllegalArgumentException("Must choose type of category");
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("This category does not exist"));
        product.setCategory(category);

        MultipartFile imageFile = request.getImageFile();
        if (imageFile != null && !imageFile.isEmpty()) {
            try {
                product.setImage(imageFile.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read image file", e);
            }
        }

    }
    public Product createFromRequest(ProductCreationRequest request) {
        Product product = new Product();
        mapRequestToProduct(request, product);
        return productRepository.save(product);
    }

    // Update product
    public Product updateProduct(Integer id, ProductCreationRequest request){
        Optional<Product> existingProductOptional = productRepository.findById(id);
        if(existingProductOptional.isEmpty()){
            return null;
        }
        Product existingProduct = existingProductOptional.get();
        mapRequestToProduct(request, existingProduct);
        return productRepository.save(existingProduct);
    }

    // Delete product
    public  void deleteProduct(Integer id){
        productRepository.deleteById(id);
    }
}
