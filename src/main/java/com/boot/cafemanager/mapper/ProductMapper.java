package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.ProductEntity;
import com.boot.cafemanager.service.product.dto.ProductCreateDTO;
import com.boot.cafemanager.service.product.dto.ProductDTO;
import com.boot.cafemanager.web.rest.model.product.ProductCreateModel;
import com.boot.cafemanager.web.rest.model.product.ProductModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toDTO(ProductEntity productEntity);

    ProductCreateDTO toDTO(ProductCreateModel productCreateModel);

    ProductModel toModel(ProductDTO productDTO);

}
