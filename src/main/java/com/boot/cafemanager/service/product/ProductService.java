package com.boot.cafemanager.service.product;

import com.boot.cafemanager.service.product.dto.ProductCreateDTO;
import com.boot.cafemanager.service.product.dto.ProductDTO;

import java.util.List;

public interface ProductService {

    ProductDTO create(ProductCreateDTO createDTO);

    ProductDTO getById(Long id);

    List<ProductDTO> getAll();

}
