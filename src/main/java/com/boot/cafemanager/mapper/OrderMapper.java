package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.OrderEntity;
import com.boot.cafemanager.service.order.dto.OrderCreateDTO;
import com.boot.cafemanager.service.order.dto.OrderDTO;
import com.boot.cafemanager.web.rest.model.order.OrderCreateModel;
import com.boot.cafemanager.web.rest.model.order.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDTO(OrderEntity orderEntity);

    OrderCreateDTO toDTO(OrderCreateModel orderCreateModel);

    OrderModel toModel(OrderDTO orderDTO);

}
