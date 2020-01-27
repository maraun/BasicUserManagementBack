package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.NationalityDto;
import kz.rbasicb.RBasicB.models.entities.profile.Nationality;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NationalityMapper extends AbstractModelMapper<Nationality, NationalityDto> {
    private ModelMapper modelMapper;
    @Autowired
    public NationalityMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public NationalityDto toDto(Nationality nationality) {
        return modelMapper.map(nationality, NationalityDto.class);
    }

    @Override
    public Nationality toEntity(NationalityDto nationalityDto) {
        return modelMapper.map(nationalityDto, Nationality.class);
    }

    @Override
    public List<NationalityDto> toDtoList(List<Nationality> nationalities) {
        return nationalities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Nationality> toEntityList(List<NationalityDto> nationalityDtos) {
        return nationalityDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
