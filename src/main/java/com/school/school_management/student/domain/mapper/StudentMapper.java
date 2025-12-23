package com.school.school_management.student.domain.mapper;

import com.school.school_management.Base.EntityMapper;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.model.Student;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {AddressMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {
}
