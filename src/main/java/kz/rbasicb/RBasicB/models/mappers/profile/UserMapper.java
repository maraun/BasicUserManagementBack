package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.UserDto;
import kz.rbasicb.RBasicB.models.entities.profile.User;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper extends AbstractModelMapper<User, UserDto> {
    private ModelMapper modelMapper;
    private RoleMapper roleMapper;
    private ProfileMapper profileMapper;
    private DocumentMapper documentMapper;
    private PositionMapper positionMapper;
    private ContactsMapper contactsMapper;
    private AdditionalMapper additionalMapper;

    @Autowired
    public UserMapper (ModelMapper modelMapper,
                       RoleMapper roleMapper,
                       ProfileMapper profileMapper,
                       DocumentMapper documentMapper,
                       PositionMapper positionMapper,
                       ContactsMapper contactsMapper,
                       AdditionalMapper additionalMapper){
        this.modelMapper = modelMapper;
        this.roleMapper = roleMapper;
        this.profileMapper = profileMapper;
        this.documentMapper = documentMapper;
        this.positionMapper = positionMapper;
        this.contactsMapper = contactsMapper;
        this.additionalMapper = additionalMapper;
    }
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);
        if (user.getRoles() != null) {
            userDto.setRoles(roleMapper.toDtoSet(user.getRoles()));
        }
        if (user.getProfile() != null) {
            userDto.setProfile(profileMapper.toDto(user.getProfile()));
        }
        if (user.getDocuments() != null){
            userDto.setDocuments(documentMapper.toDtoSet(user.getDocuments()));
        }
        if (user.getPositions() != null){
            userDto.setPositions(positionMapper.toDtoSet(user.getPositions()));
        }
        if (user.getContacts() != null){
            userDto.setContacts(contactsMapper.toDto(user.getContacts()));
        }
        if (user.getAdditional() != null){
            userDto.setAdditional(additionalMapper.toDto(user.getAdditional()));
        }
        return userDto;
    }

    @Override
    public User toEntity(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        if (userDto.getRoles() != null) {
            user.setRoles(roleMapper.toEntitySet(userDto.getRoles()));
        }
        if (userDto.getProfile() != null) {
            user.setProfile(profileMapper.toEntity(userDto.getProfile()));
        }
        if (userDto.getDocuments() != null) {
            user.setDocuments(documentMapper.toEntitySet(userDto.getDocuments()));
        }
        if (userDto.getPositions() != null) {
            user.setPositions(positionMapper.toEntitySet(userDto.getPositions()));
        }
        if (userDto.getContacts() != null){
            user.setContacts(contactsMapper.toEntity(userDto.getContacts()));
        }
        if (userDto.getContacts() != null){
            user.setAdditional(additionalMapper.toEntity(userDto.getAdditional()));
        }
        return user;
    }

    @Override
    public List<UserDto> toDtoList(List<User> users) {
        return users.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> toEntityList(List<UserDto> userDtos) {
        return userDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
