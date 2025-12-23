package com.school.school_management.student.domain.api;

import com.school.school_management.Base.Commands.CommandDispatcher;
import com.school.school_management.student.domain.Commands.AddressCommandResult;
import com.school.school_management.student.domain.Commands.CreateAddressCommand;
import com.school.school_management.student.domain.dto.AddressDTO;
import com.school.school_management.student.domain.repository.AddressRepository;
import com.school.school_management.student.domain.service.AddressQueryService;
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
@RequestMapping("api/address")
public class AddressContro {

    final CommandDispatcher commandDispatcher;
    final AddressQueryService addressQueryService;

    @PostMapping("")
    ResponseEntity<AddressDTO> create(@Valid @RequestBody AddressDTO dto) {
        AddressCommandResult result = commandDispatcher.dispatch(
                CreateAddressCommand.builder()
                        .addressDTO(dto)
                        .build()
        );
        return ResponseEntity.ok(result.getCity());
    }
}
