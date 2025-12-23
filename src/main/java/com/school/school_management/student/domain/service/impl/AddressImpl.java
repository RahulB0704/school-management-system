package com.school.school_management.student.domain.service.impl;

import com.school.school_management.Base.CRUDServiceImpl;
import com.school.school_management.student.domain.dto.AddressDTO;
import com.school.school_management.student.domain.model.Address;
import com.school.school_management.student.domain.service.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressImpl extends CRUDServiceImpl<AddressDTO, Address> implements AddressService {
}
