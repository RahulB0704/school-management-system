package com.school.school_management.student.domain.dto;

import com.school.school_management.Base.BaseDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO extends BaseDTO {

    @NotBlank(message = "Name is required")
    String name;

    @NotBlank(message = "Standard is required")
    String standard;

    String division;

    @NotBlank(message = "Parent Contact is mandatory")
    String parentContact;

    Boolean active = true;
}
