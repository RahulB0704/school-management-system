package com.school.school_management.student.domain.repository;

import com.school.school_management.Base.CRUDRepository;
import com.school.school_management.student.domain.model.Student;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping
public interface StudentRepository extends CRUDRepository<Student> {


}
