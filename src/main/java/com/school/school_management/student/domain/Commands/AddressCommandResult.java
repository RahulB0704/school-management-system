package com.school.school_management.student.domain.Commands;

import com.school.school_management.Base.Commands.CommandResult;
import com.school.school_management.student.domain.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class AddressCommandResult extends CommandResult {

    AddressDTO city;
}
