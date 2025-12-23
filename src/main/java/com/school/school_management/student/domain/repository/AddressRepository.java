package com.school.school_management.student.domain.repository;

import com.school.school_management.Base.CRUDRepository;
import com.school.school_management.student.domain.model.Address;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CRUDRepository<Address> {

}
