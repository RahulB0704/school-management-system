package com.school.school_management.student.domain.Commands;

import com.school.school_management.Base.Commands.CommandResult;
import com.school.school_management.student.domain.dto.StudentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StudentCommandResult extends CommandResult {
StudentDTO student;
}
