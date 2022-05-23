package org.maxkizi.shortlink.authenticationservice.converter;

import org.maxkizi.shortlink.authenticationservice.dto.ApplicationUserDto;
import org.maxkizi.shortlink.common.model.ApplicationUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ApplicationUserConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public ApplicationUser fromDto(ApplicationUserDto dto){
        return modelMapper.map(dto, ApplicationUser.class);
    }
}
