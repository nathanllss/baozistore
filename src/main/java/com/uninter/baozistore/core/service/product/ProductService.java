package com.uninter.baozistore.core.service.product;

import com.uninter.baozistore.core.common.Messages;
import com.uninter.baozistore.core.model.Product;
import com.uninter.baozistore.core.repository.product.ProductRepository;
import com.uninter.baozistore.infrastructure.controller.product.ProductInput;
import com.uninter.baozistore.infrastructure.controller.product.ProductOutput;
import com.uninter.baozistore.infrastructure.exception.NotFoundException;
import com.uninter.baozistore.infrastructure.exception.ProductAlreadyExistsException;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public ProductOutput createProduct(ProductInput productInput) {
        if (validateExistingProduct(productInput.getName())) {
            throw new ProductAlreadyExistsException("Product with name '" + productInput.getName() + "' already exists");
        }
        Product product = Product.builder()
                .name(productInput.getName())
                .price(productInput.getPrice())
                .inStock(productInput.getInStock())
                .build();
        product = productRepository.save(product);
        return new ProductOutput(product.getId(), product.getName(), product.getPrice(), product.getInStock());
    }

    @Transactional
    public ProductOutput updateProduct(Long id, ProductInput productInput) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.PRODUCT_NOT_FOUND + " " + id));
        product.setName(productInput.getName());
        product.setPrice(productInput.getPrice());
        product.setInStock(productInput.getInStock());
        product = productRepository.save(product);
        return new ProductOutput(product.getId(), product.getName(), product.getPrice(), product.getInStock());
    }

    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Messages.PRODUCT_NOT_FOUND + " " + id));
        productRepository.delete(product);
    }

    @Transactional(readOnly = true)
    public List<ProductOutput> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductOutput(product.getId(), product.getName(), product.getPrice(), product.getInStock()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ProductOutput getProductById(Long id) {
        return productRepository.findById(id)
                .map(product -> new ProductOutput(product.getId(), product.getName(), product.getPrice(), product.getInStock()))
                .orElseThrow(() -> new NotFoundException(Messages.PRODUCT_NOT_FOUND + " " + id));
    }

    private boolean validateExistingProduct(@NotBlank String name) {
        return productRepository.existsByName(name);
    }
}
