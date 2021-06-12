package com.boot.cafemanager.web.rest.controller;

import com.boot.cafemanager.mapper.ProductMapper;
import com.boot.cafemanager.service.product.ProductService;
import com.boot.cafemanager.service.product.dto.ProductCreateDTO;
import com.boot.cafemanager.service.product.dto.ProductDTO;
import com.boot.cafemanager.web.rest.model.product.ProductCreateModel;
import com.boot.cafemanager.web.rest.model.product.ProductModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @PostMapping
    public ResponseEntity<ProductModel> create(@RequestBody @Valid ProductCreateModel productCreateModel) {
        ProductCreateDTO productCreateDTO = productMapper.toDTO(productCreateModel);
        ProductDTO productDTO = productService.create(productCreateDTO);
        ProductModel productModel = productMapper.toModel(productDTO);
        return ResponseEntity.ok(productModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductModel> getById(@PathVariable Long id) {
        ProductDTO productDTO = productService.getById(id);
        ProductModel productModel = productMapper.toModel(productDTO);
        return ResponseEntity.ok(productModel);
    }

    @GetMapping
    public ResponseEntity<List<ProductModel>> getAll() {
        List<ProductDTO> productDTOs = productService.getAll();
        List<ProductModel> productModels = productDTOs.stream().map(productMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(productModels);
    }

}
