package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import kz.rbasicb.RBasicB.models.dtos.profile.CitizenshipDto;
import kz.rbasicb.RBasicB.models.entities.profile.Citizenship;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CitizenshipMapper extends AbstractModelMapper<Citizenship, CitizenshipDto>{
    private ModelMapper modelMapper;

    @Autowired
    public CitizenshipMapper (ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    @Override
    public CitizenshipDto toDto(Citizenship citizenship) {
        return modelMapper.map(citizenship, CitizenshipDto.class);
    }

    @Override
    public Citizenship toEntity(CitizenshipDto citizenshipDto) {
        return modelMapper.map(citizenshipDto, Citizenship.class);
    }

    @Override
    public List<CitizenshipDto> toDtoList(List<Citizenship> citizenships) {
        return citizenships.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Citizenship> toEntityList(List<CitizenshipDto> citizenshipDtos) {
        return citizenshipDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
