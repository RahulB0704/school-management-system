package com.school.school_management.student.domain.Commands;

import com.school.school_management.Base.Commands.AbstractCommandExecutor;
import com.school.school_management.Base.Commands.CommandExecutor;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateStudentCommandExecutor extends AbstractCommandExecutor<CreateStudentCommand, StudentCommandResult>
implements CommandExecutor<CreateStudentCommand, StudentCommandResult> {

    final StudentService studentService;
    @Override
    protected StudentCommandResult process(CreateStudentCommand command) {
        StudentDTO createDto = studentService.create(command.getStudent());
        return StudentCommandResult.builder()
                .student(createDto)
                .build();
    }
}
