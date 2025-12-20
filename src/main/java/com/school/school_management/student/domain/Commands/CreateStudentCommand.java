package com.school.school_management.student.domain.Commands;

import com.school.school_management.Base.Commands.Command;
import com.school.school_management.student.domain.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand extends Command {
    StudentDTO student;
}
