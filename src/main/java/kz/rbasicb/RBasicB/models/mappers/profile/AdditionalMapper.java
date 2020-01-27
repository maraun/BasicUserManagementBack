package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.AdditionalDto;
import kz.rbasicb.RBasicB.models.entities.profile.Additional;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AdditionalMapper extends AbstractModelMapper<Additional, AdditionalDto> {
    private ModelMapper modelMapper;

    @Autowired
    public AdditionalMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AdditionalDto toDto(Additional additional) {
        return modelMapper.map(additional, AdditionalDto.class);
    }

    @Override
    public Additional toEntity(AdditionalDto additionalDto) {
        return modelMapper.map(additionalDto, Additional.class);
    }

    @Override
    public List<AdditionalDto> toDtoList(List<Additional> additionals) {
        return additionals.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Additional> toEntityList(List<AdditionalDto> additionalDtos) {
        return additionalDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
