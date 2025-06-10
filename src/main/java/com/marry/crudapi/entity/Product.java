package com.marry.crudapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;


}
