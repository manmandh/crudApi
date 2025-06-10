package com.marry.crudapi.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ProductCreationRequest {
    private String name;
    private String image;
    private Double price;
    private Integer categoryId;
    private MultipartFile imageFile;
}
