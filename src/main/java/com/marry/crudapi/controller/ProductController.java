package com.marry.crudapi.controller;

import com.marry.crudapi.dto.request.ProductCreationRequest;
import com.marry.crudapi.entity.Product;
import com.marry.crudapi.service.CategoryService;
import com.marry.crudapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RestController
@RequestMapping("/admin/product")
@CrossOrigin(origins = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

//    // Render list products
//    @GetMapping("/index")
//    public String list(Model model) {
//        model.addAttribute("products", productService.getAll());
//        return "admin/product";
//    }
//
//    // Render form add product
//    @GetMapping("/create")
//    public String createForm(Model model) {
//        model.addAttribute("request", new ProductCreationRequest());
//        model.addAttribute("categories", categoryService.getAll());
//        return "admin/product-form";
//    }
//
//    // Submit form add product
//    @PostMapping("/create")
//    public String createSubmit(@ModelAttribute("request") ProductCreationRequest request) {
//        productService.createFromRequest(request);
//        return "redirect:/admin/product/index";
//    }
//
//    @GetMapping("/image/{id}")
//    @ResponseBody
//    public ResponseEntity<byte[]> serveImage(@PathVariable Integer id) {
//        Product product = productService.getById(id);
//        if (product == null || product.getImage() == null) {
//            return ResponseEntity.notFound().build();
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.IMAGE_JPEG);
//        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
//    }
//
//    @GetMapping("/delete/{id}")
//    public String delete(@PathVariable Integer id, RedirectAttributes redirectAttributes){
//        try {
//            productService.deleteProduct(id);
//            redirectAttributes.addFlashAttribute("success", "Product deleted successfully");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//        }
//        return "redirect:/admin/product/index";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String editForm(@PathVariable Integer id, Model model) {
//        Product product = productService.getById(id);
//        if (product == null) {
//            return "redirect:/admin/product/index";
//        }
//        // Create ProductCreationRequest from information product to fill form edit
//        ProductCreationRequest request = new ProductCreationRequest();
//        request.setName(product.getName());
//        request.setPrice(product.getPrice());
//        request.setCategoryId(product.getCategory().getId());
//
//        model.addAttribute("product", product);
//        model.addAttribute("request", request);
//        model.addAttribute("categories", categoryService.getAll());
//        return "admin/product-edit-form";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String editSubmit(@PathVariable Integer id, @ModelAttribute("request") ProductCreationRequest request, RedirectAttributes redirectAttributes) {
//        try {
//            Product editProduct = productService.updateProduct(id, request);
//            if(editProduct == null){
//                redirectAttributes.addFlashAttribute("error", "Product not found");
//                return "redirect:/admin/product/index";
//            } else{
//                redirectAttributes.addFlashAttribute("success", "Product edited successfully");
//            }
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("error", e.getMessage());
//            return "redirect:/admin/product/edit/" + id;
//        }
//        return "redirect:/admin/product/index";
//    }

    @GetMapping("/call")
    public ResponseEntity<List<Product>> getProductByAPI(Model model) {
//        model.addAttribute("products", productService.getAll());
        return ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer id) {
        Product product = productService.getById(id);
        return product != null ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> createProduct(@ModelAttribute ProductCreationRequest request) {
        Product created = productService.createFromRequest(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @ModelAttribute ProductCreationRequest request) {
        Product updated = productService.updateProduct(id, request);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id) {
        try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Delete failed: " + e.getMessage());
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer id) {
        Product product = productService.getById(id);
        if (product == null || product.getImage() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<>(product.getImage(), headers, HttpStatus.OK);
    }
}
