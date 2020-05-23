package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.GroupRolesDto;
import kz.rbasicb.RBasicB.models.entities.profile.GroupRoles;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class GroupRolesMapper extends AbstractModelMapper<GroupRoles, GroupRolesDto> {
    private ModelMapper modelMapper;
    @Autowired
    public GroupRolesMapper(ModelMapper modelMapper) {this.modelMapper = modelMapper;}
    @Override
    public GroupRolesDto toDto(GroupRoles groupRoles) {
        return modelMapper.map(groupRoles, GroupRolesDto.class);
    }

    @Override
    public GroupRoles toEntity(GroupRolesDto groupRolesDto) {
        return modelMapper.map(groupRolesDto, GroupRoles.class);
    }

    @Override
    public List<GroupRolesDto> toDtoList(List<GroupRoles> groupRoles) {
        return groupRoles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<GroupRoles> toEntityList(List<GroupRolesDto> groupRolesDtos) {
        return groupRolesDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
