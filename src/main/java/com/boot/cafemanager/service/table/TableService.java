package com.boot.cafemanager.service.table;

import com.boot.cafemanager.service.table.dto.TableCreateDTO;
import com.boot.cafemanager.service.table.dto.TableDTO;

import java.util.List;

public interface TableService {

  TableDTO create(TableCreateDTO createDTO);

  TableDTO getById(Long id);

  void assign(Long tableId,Long waiterId);

  List<TableDTO> getAll();

  List<TableDTO> getAllByWaiterId(Long waiterId);

  void delete(Long id);
}
