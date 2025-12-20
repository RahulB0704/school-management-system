package com.school.school_management.student.domain.service.impl;

import com.school.school_management.Base.CRUDRepository;
import com.school.school_management.Base.CRUDServiceImpl;
import com.school.school_management.Base.EntityMapper;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.model.Student;
import com.school.school_management.student.domain.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
public class StudentImpl extends CRUDServiceImpl<StudentDTO, Student> implements StudentService {

    public StudentImpl(CRUDRepository<Student> repository, EntityMapper<StudentDTO, Student> mapper) {
        super(repository, mapper);
    }
}
