package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.GenderDto;
import kz.rbasicb.RBasicB.models.entities.profile.Gender;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GenderMapper extends AbstractModelMapper<Gender, GenderDto> {
    private ModelMapper modelMapper;

    @Autowired
    public GenderMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public GenderDto toDto(Gender gender) {
        return modelMapper.map(gender, GenderDto.class);
    }

    @Override
    public Gender toEntity(GenderDto genderDto) {
        return modelMapper.map(genderDto, Gender.class);
    }

    @Override
    public List<GenderDto> toDtoList(List<Gender> genders) {
        return genders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Gender> toEntityList(List<GenderDto> genderDtos) {
        return genderDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
