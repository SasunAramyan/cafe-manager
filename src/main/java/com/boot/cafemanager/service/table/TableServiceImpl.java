package com.boot.cafemanager.service.table;

import com.boot.cafemanager.data.jpa.entity.TableEntity;
import com.boot.cafemanager.data.jpa.entity.UserEntity;
import com.boot.cafemanager.data.jpa.repository.TableRepository;
import com.boot.cafemanager.data.jpa.repository.UserRepository;
import com.boot.cafemanager.exception.TableAlreadyExistsException;
import com.boot.cafemanager.exception.TableAlreadyIsUsedException;
import com.boot.cafemanager.exception.TableNotFoundException;
import com.boot.cafemanager.exception.UserNotFoundException;
import com.boot.cafemanager.mapper.TableMapper;
import com.boot.cafemanager.service.table.dto.TableCreateDTO;
import com.boot.cafemanager.service.table.dto.TableDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
public class TableServiceImpl implements TableService {

    private static final String ROLE_WAITER = "ROLE_WAITER";
    private final TableRepository tableRepository;
    private final UserRepository userRepository;
    private final TableMapper tableMapper;

    public TableServiceImpl(TableRepository tableRepository, UserRepository userRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.userRepository = userRepository;
        this.tableMapper = tableMapper;
    }

    @Override
    @Transactional
    public TableDTO create(TableCreateDTO createDTO) {
        validateTableUniqueFields(createDTO);
        TableEntity tableEntity = new TableEntity();
        tableEntity.setNumber(createDTO.getNumber());
        tableRepository.save(tableEntity);
        return tableMapper.toDTO(tableEntity);
    }


    @Override
    @Transactional(readOnly = true)
    public TableDTO getById(Long id) {
        TableEntity tableEntity = getTableById(id);
        return tableMapper.toDTO(tableEntity);
    }

    @Override
    @Transactional
    public void assign(Long tableId, Long waiterId) {
        UserEntity waiter = userRepository.findByIdAndRoles_NameAndDeletedIsFalse(waiterId, ROLE_WAITER)
                .orElseThrow(() -> new UserNotFoundException(String.format("id : %s role : %s", waiterId, ROLE_WAITER)));
        TableEntity table = getTableById(tableId);
        throwIfTableAlreadyHasWaiter(table);
        table.setWaiter(waiter);
        tableRepository.save(table);
    }


    @Override
    @Transactional(readOnly = true)
    public List<TableDTO> getAll() {
        List<TableEntity> tableEntities = tableRepository.findAllByDeletedIsFalse();
        return tableEntities.stream().map(tableMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<TableDTO> getAllByWaiterId(Long waiterId) {
        List<TableEntity> tableEntities = tableRepository.findAllByWaiter_IdAndDeletedIsFalse(waiterId);
        return tableEntities.stream().map(tableMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void delete(Long id) {
        TableEntity tableEntity = getTableById(id);
        if (nonNull(tableEntity.getWaiter())) {
            //todo log delete table what has waiter
        }
        tableEntity.setDeleted(true);
        tableRepository.save(tableEntity);
    }


    private TableEntity getTableById(Long id) {
        return tableRepository.findByIdAndDeletedIsFalse(id)
                .orElseThrow(() -> new TableNotFoundException(id));
    }

    private void validateTableUniqueFields(TableCreateDTO createDTO) {
        validateUniqueNumber(createDTO.getNumber());
    }

    private void validateUniqueNumber(Long number) {
        if (tableRepository.existsByNumber(number)) {
            throw new TableAlreadyExistsException("number", number);
        }
    }

    private void throwIfTableAlreadyHasWaiter(TableEntity table) {
        if (nonNull(table.getWaiter())) {
            throw new TableAlreadyIsUsedException(table.getId());
        }
    }
}
