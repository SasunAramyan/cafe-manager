package com.boot.cafemanager.service.product;

import com.boot.cafemanager.data.jpa.entity.ProductEntity;
import com.boot.cafemanager.data.jpa.repository.ProductRepository;
import com.boot.cafemanager.data.jpa.repository.UserRepository;
import com.boot.cafemanager.exception.ProductAlreadyExistsException;
import com.boot.cafemanager.exception.ProductNotFoundException;
import com.boot.cafemanager.mapper.ProductMapper;
import com.boot.cafemanager.service.product.dto.ProductCreateDTO;
import com.boot.cafemanager.service.product.dto.ProductDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public ProductDTO create(ProductCreateDTO createDTO) {
        validateProductUniqueFields(createDTO);
        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(createDTO.getName());
        productRepository.save(productEntity);
        return productMapper.toDTO(productEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public ProductDTO getById(Long id) {
        ProductEntity productEntity = getProductById(id);
        return productMapper.toDTO(productEntity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductDTO> getAll() {
        List<ProductEntity> productEntities = productRepository.findAll();
        return productEntities.stream().map(productMapper::toDTO).collect(Collectors.toList());
    }

    private ProductEntity getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    private void validateProductUniqueFields(ProductCreateDTO createDTO) {
        validateUniqueName(createDTO.getName());
    }

    private void validateUniqueName(String name) {
        if (productRepository.existsByName(name)) {
            throw new ProductAlreadyExistsException("name", name);
        }
    }
}
