package com.school.school_management.student.domain.service;


import com.school.school_management.Base.EntityMapper;
import com.school.school_management.Base.PagedResoure;
import com.school.school_management.Base.query.QueryService;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.mapper.StudentMapper;
import com.school.school_management.student.domain.model.Student;
import com.school.school_management.student.domain.query.StudentCriteria;
import com.school.school_management.student.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentQueryService extends QueryService<Student> {

    @Autowired
    StudentRepository repository;
    @Autowired
    StudentMapper mapper;

    public PagedResoure<Student> findByCriteria(StudentCriteria criteria, Pageable page) {
        return findByCriteria(criteria, page, mapper);
    }

    public <T> PagedResoure<T> findByCriteria(StudentCriteria criteria, Pageable page, EntityMapper mapper) {

        Page<Student> entities = repository.findAll(createSpecification(criteria), page);
        PagedResoure<T> dtoPage =
                new PagedResoure<>(
                        (List<T>) entities.getContent().stream().map(mapper::toDto).collect(Collectors.toList()),

                        new PagedResoure.PageMetadata(entities.getSize(),
                                entities.getTotalElements(),
                                entities.getTotalPages(),
                                entities.getNumber())
                );
        return dtoPage;
    }

    private Specification<Student> createSpecification(StudentCriteria criteria) {
        return  null;
    }
}
