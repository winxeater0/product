package com.example.product.controller;

import com.example.product.dto.*;
import com.example.product.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody CreateProductRequest request,
            HttpServletRequest httpRequest) {

        ProductResponse response = service.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ApiResponseBuilder.success(
                        HttpStatus.CREATED,
                        response,
                        "Created Successfully",
                        httpRequest
                ));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> findById(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        HttpStatus.OK,
                        service.findById(id),
                        "Success",
                        httpRequest
                )
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<ProductResponse>>> findAll(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            HttpServletRequest httpRequest) {

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        HttpStatus.OK,
                        service.findAll(pageable),
                        "Success",
                        httpRequest
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request,
            HttpServletRequest httpRequest) {

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        HttpStatus.OK,
                        service.update(id, request),
                        "Updated Successfully",
                        httpRequest
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(
            @PathVariable Long id,
            HttpServletRequest httpRequest) {

        service.delete(id);

        return ResponseEntity.ok(
                ApiResponseBuilder.success(
                        HttpStatus.OK,
                        null,
                        "Deleted Successfully",
                        httpRequest
                )
        );
    }
}



