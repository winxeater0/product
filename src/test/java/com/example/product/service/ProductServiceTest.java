package com.example.product.service;

import com.example.product.domain.model.Product;
import com.example.product.dto.CreateProductRequest;
import com.example.product.dto.ProductResponse;
import com.example.product.dto.UpdateProductRequest;
import com.example.product.mapper.ProductMapper;
import com.example.product.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.lenient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductService service;

    @Test
    void shouldCreateProductSuccessfully() {
        // Arrange
        CreateProductRequest request = new CreateProductRequest("Notebook", "Description", BigDecimal.TEN);

        Product product = new Product();
        product.setName("Notebook");
        product.setDescription("Description");
        product.setPrice(BigDecimal.TEN);

        Product saved = new Product();
        saved.setId(1L);
        saved.setName("Notebook");
        saved.setDescription("Description");
        saved.setPrice(BigDecimal.TEN);
        saved.setCreatedAt(LocalDateTime.now());

        ProductResponse response = new ProductResponse(
                1L, "Notebook", "Description", BigDecimal.TEN, saved.getCreatedAt()
        );

        // Garante que o mapper.toEntity não retorna null
        doReturn(product).when(mapper).toEntity(eq(request));
        doReturn(response).when(mapper).toResponse(eq(saved));

        // Garante que o repository.save retorna o objeto salvo
        doReturn(saved).when(repository).save(eq(product));

        // Act
        ProductResponse result = service.create(request);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Notebook", result.name());

        verify(repository, times(1)).save(eq(product));
        verify(mapper, times(1)).toEntity(eq(request));
        verify(mapper, times(1)).toResponse(eq(saved));
    }

    @Test
    void shouldUpdateProductSuccessfully() {

        Long id = 1L;

        UpdateProductRequest request =
                new UpdateProductRequest("Updated", "Desc", BigDecimal.ONE);

        Product product = new Product();
        product.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(product));
        when(repository.save(product)).thenReturn(product);
        when(mapper.toResponse(product))
                .thenReturn(new ProductResponse(id, "Updated", "Desc", BigDecimal.ONE, null));

        ProductResponse response = service.update(id, request);

        assertEquals("Updated", response.name());
        verify(repository).findById(id);
    }

    @Test
    void shouldDeleteProductSuccessfully() {

        Long id = 1L;
        Product product = new Product();
        product.setId(id);

        when(repository.findById(id)).thenReturn(Optional.of(product));

        service.delete(id);

        verify(repository).delete(product);
    }

}
