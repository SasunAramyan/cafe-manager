package com.boot.cafemanager.web.rest.controller;

import com.boot.cafemanager.mapper.TableMapper;
import com.boot.cafemanager.service.table.TableService;
import com.boot.cafemanager.service.table.dto.TableCreateDTO;
import com.boot.cafemanager.service.table.dto.TableDTO;
import com.boot.cafemanager.web.rest.model.table.TableCreateModel;
import com.boot.cafemanager.web.rest.model.table.TableModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class TableController {

    private final TableService tableService;
    private final TableMapper tableMapper;

    public TableController(TableService tableService, TableMapper tableMapper) {
        this.tableService = tableService;
        this.tableMapper = tableMapper;
    }

    @PostMapping("/tables")
    public ResponseEntity<TableModel> create(@RequestBody @Valid TableCreateModel tableCreateModel) {
        TableCreateDTO tableCreateDTO = tableMapper.toDTO(tableCreateModel);
        TableDTO tableDTO = tableService.create(tableCreateDTO);
        TableModel tableModel = tableMapper.toModel(tableDTO);
        return ResponseEntity.ok(tableModel);
    }

    @GetMapping("/tables/{id}")
    public ResponseEntity<TableModel> getById(@PathVariable Long id) {
        TableDTO tableDTO = tableService.getById(id);
        TableModel tableModel = tableMapper.toModel(tableDTO);
        return ResponseEntity.ok(tableModel);
    }

    @PutMapping("/tables/{tableId}/assign/users/{waiterId}")
    public void assignWaiter(@PathVariable Long tableId, @PathVariable Long waiterId) {
        tableService.assign(tableId, waiterId);
    }

    @GetMapping("/tables")
    public ResponseEntity<List<TableModel>> getAll() {
        List<TableDTO> tableDTOs = tableService.getAll();
        List<TableModel> tableModels = tableDTOs.stream().map(tableMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(tableModels);
    }

    @GetMapping("/users/{waiterId}/tables")
    public ResponseEntity<List<TableModel>> getAllByWaiter(@PathVariable Long waiterId) {
        List<TableDTO> tableDTOs = tableService.getAllByWaiterId(waiterId);
        List<TableModel> tableModels = tableDTOs.stream().map(tableMapper::toModel).collect(Collectors.toList());
        return ResponseEntity.ok(tableModels);
    }

    @DeleteMapping("/tables/{id}")
    public void deleteById(@PathVariable Long id) {
        tableService.delete(id);
    }
}
