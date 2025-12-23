package com.school.school_management.student.domain.mapper;

import com.school.school_management.Base.EntityMapper;
import com.school.school_management.student.domain.dto.AddressDTO;
import com.school.school_management.student.domain.model.Address;
import org.mapstruct.Mapper;

import javax.management.MXBean;

@Mapper(componentModel = "spring",uses = {})
public interface AddressMapper extends EntityMapper<AddressDTO, Address> {
}
