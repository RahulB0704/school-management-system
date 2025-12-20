package com.school.school_management.student.domain.api;


import com.school.school_management.Base.Commands.CommandDispatcher;
import com.school.school_management.student.domain.Commands.CreateStudentCommand;
import com.school.school_management.student.domain.Commands.StudentCommandResult;
import com.school.school_management.student.domain.dto.StudentDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
@RequestMapping("api/student")
public class StudentContro {

    final CommandDispatcher CommandDispatcher;

    @PostMapping("")
    ResponseEntity<StudentDTO> create(@Valid @RequestBody StudentDTO dto) {
        StudentCommandResult result =
                CommandDispatcher.dispatch(
                        CreateStudentCommand.builder()
                          .student(dto)
                                .build());

  return ResponseEntity.ok(result.getStudent());
    }
}
