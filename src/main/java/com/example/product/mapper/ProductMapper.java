package com.example.product.mapper;

import com.example.product.domain.model.Product;
import com.example.product.dto.CreateProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.dto.UpdateProductRequest;
import org.springframework.stereotype.Component;

import java.math.RoundingMode;

@Component
public class ProductMapper {

    public Product toEntity(CreateProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .price(request.price().setScale(2, RoundingMode.HALF_UP))
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCreatedAt()
        );
    }

    public void updateEntity(Product product, UpdateProductRequest request) {
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price().setScale(2, RoundingMode.HALF_UP));
    }
}

