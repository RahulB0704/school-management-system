package com.school.school_management.student.domain.dto;

import com.school.school_management.Base.BaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO extends BaseDTO {

    String addressLine;

    String area;

    String city;

    String state;

    String zipcode;

    String country;
}
