package com.school.school_management.student.domain.api;


import com.school.school_management.Base.Commands.CommandDispatcher;
import com.school.school_management.Base.ResponseUtil;
import com.school.school_management.student.domain.Commands.CreateStudentCommand;
import com.school.school_management.student.domain.Commands.StudentCommandResult;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.query.StudentCriteria;
import com.school.school_management.student.domain.repository.StudentRepository;
import com.school.school_management.student.domain.service.StudentQueryService;
import com.school.school_management.student.domain.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/student")
public class StudentContro {

    final CommandDispatcher CommandDispatcher;

    final StudentQueryService studentQueryService;

    final StudentService studentService;

    @PostMapping("")
    ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        StudentCommandResult result =
                CommandDispatcher.dispatch(
                        CreateStudentCommand.builder()
                          .student(dto)
                                .build());

  return ResponseEntity.ok(result.getStudent());
    }

    @GetMapping("")
    ResponseEntity<?> list(StudentCriteria criteria, final Pageable pageable) {
        return ResponseEntity.ok(studentQueryService.findByCriteria(criteria,pageable));
    }

    @GetMapping("{id}")
   public ResponseEntity<StudentDTO> getById(@PathVariable String id) {
        return ResponseUtil.wrapOrNotFound(studentService.findById(id));
   }

}
