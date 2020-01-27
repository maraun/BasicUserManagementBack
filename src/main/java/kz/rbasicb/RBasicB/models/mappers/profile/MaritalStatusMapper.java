package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.MaritalStatusDto;
import kz.rbasicb.RBasicB.models.entities.profile.MaritalStatus;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MaritalStatusMapper extends AbstractModelMapper<MaritalStatus, MaritalStatusDto> {
    private ModelMapper modelMapper;

    @Autowired
    public MaritalStatusMapper (ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public MaritalStatusDto toDto(MaritalStatus maritalStatus) {
        return modelMapper.map(maritalStatus, MaritalStatusDto.class);
    }

    @Override
    public MaritalStatus toEntity(MaritalStatusDto maritalStatusDto) {
        return modelMapper.map(maritalStatusDto, MaritalStatus.class);
    }

    @Override
    public List<MaritalStatusDto> toDtoList(List<MaritalStatus> maritalStatuses) {
        return maritalStatuses.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<MaritalStatus> toEntityList(List<MaritalStatusDto> maritalStatusDtos) {
        return maritalStatusDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
