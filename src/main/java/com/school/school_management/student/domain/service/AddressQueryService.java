package com.school.school_management.student.domain.service;

import com.school.school_management.Base.PagedResoure;
import com.school.school_management.Base.query.QueryService;
import com.school.school_management.student.domain.dto.AddressDTO;
import com.school.school_management.student.domain.mapper.AddressMapper;
import com.school.school_management.student.domain.model.Address;
import com.school.school_management.student.domain.query.AddressCriteria;
import com.school.school_management.student.domain.repository.AddressRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AddressQueryService extends QueryService<AddressDTO> {

    private AddressRepository addressRepository;
    private AddressMapper addressMapper;

    public PagedResoure<AddressDTO> findByCriteria(AddressCriteria criteria, Pageable page) {

        Page<Address> entities = addressRepository.findAll(createSpecification(criteria), page);
        PagedResoure<AddressDTO> dtoPage =
                new PagedResoure<>(
                        addressMapper.toDto(entities.getContent()),
                        new PagedResoure.PageMetadata(entities.getSize(),
                                entities.getTotalElements(),
                                entities.getTotalPages(),
                                entities.getNumber())
                );
        return dtoPage;
    }

    private Example<Address> createSpecification(AddressCriteria criteria) {
        return null;
    }
}
