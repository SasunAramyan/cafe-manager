package com.boot.cafemanager.mapper;

import com.boot.cafemanager.data.jpa.entity.TableEntity;
import com.boot.cafemanager.service.table.dto.TableCreateDTO;
import com.boot.cafemanager.service.table.dto.TableDTO;
import com.boot.cafemanager.web.rest.model.table.TableCreateModel;
import com.boot.cafemanager.web.rest.model.table.TableModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TableMapper {

    TableMapper INSTANCE = Mappers.getMapper(TableMapper.class);

    TableDTO toDTO(TableEntity tableEntity);

    TableCreateDTO toDTO(TableCreateModel tableCreateModel);

    TableModel toModel(TableDTO tableDTO);

}
