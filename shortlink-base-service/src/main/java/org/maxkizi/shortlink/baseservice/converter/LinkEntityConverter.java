package org.maxkizi.shortlink.baseservice.converter;

import org.maxkizi.shortlink.baseservice.dto.LinkEntityDto;
import org.maxkizi.shortlink.common.model.LinkEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LinkEntityConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public LinkEntityDto toDto(LinkEntity linkEntity) {
        return modelMapper.map(linkEntity, LinkEntityDto.class);
    }
}
