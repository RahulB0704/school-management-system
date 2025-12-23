package com.school.school_management.student.domain.Commands;

import com.school.school_management.Base.Commands.AbstractCommandExecutor;
import com.school.school_management.Base.Commands.CommandExecutor;
import com.school.school_management.student.domain.dto.AddressDTO;
import com.school.school_management.student.domain.dto.StudentDTO;
import com.school.school_management.student.domain.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateAddressCommandExecutor extends AbstractCommandExecutor<CreateAddressCommand, AddressCommandResult>
implements CommandExecutor<CreateAddressCommand, AddressCommandResult> {
    final AddressService addressService;
    @Override
    protected AddressCommandResult process(CreateAddressCommand command) {
        AddressDTO createDto = addressService.create(command.getAddressDTO());
        AddressCommandResult result = AddressCommandResult
                .builder()
                .city(createDto)
                .build();

        return result;
    }
}
