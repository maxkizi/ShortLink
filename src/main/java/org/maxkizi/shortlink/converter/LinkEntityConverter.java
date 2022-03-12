package org.maxkizi.shortlink.converter;

import org.maxkizi.shortlink.dto.LinkEntityDto;
import org.maxkizi.shortlink.model.LinkEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class LinkEntityConverter {
    private ModelMapper modelMapper = new ModelMapper();

    public LinkEntityDto toDto(LinkEntity linkEntity) {
        return modelMapper.map(linkEntity, LinkEntityDto.class);
    }
}
