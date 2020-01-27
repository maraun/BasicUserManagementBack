package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.ProfileDto;
import kz.rbasicb.RBasicB.models.entities.profile.Profile;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfileMapper extends AbstractModelMapper<Profile, ProfileDto> {
    private ModelMapper modelMapper;
    private NationalityMapper nationalityMapper;
    private CitizenshipMapper citizenshipMapper;
    private GenderMapper genderMapper;
    private MaritalStatusMapper maritalStatusMapper;

    @Autowired
    public ProfileMapper (ModelMapper modelMapper,
                          NationalityMapper nationalityMapper,
                          CitizenshipMapper citizenshipMapper,
                          GenderMapper genderMapper,
                          MaritalStatusMapper maritalStatusMapper){
        this.modelMapper = modelMapper;
        this.nationalityMapper = nationalityMapper;
        this.citizenshipMapper = citizenshipMapper;
        this.genderMapper = genderMapper;
        this.maritalStatusMapper = maritalStatusMapper;
    }
    @Override
    public ProfileDto toDto(Profile profile) {
        ProfileDto profileDto = modelMapper.map(profile, ProfileDto.class);
        if (profile.getNationality() != null){
            profileDto.setNationality(nationalityMapper.toDto(profile.getNationality()));
        }
        if (profile.getCitizenship() != null){
            profileDto.setCitizenship(citizenshipMapper.toDto(profile.getCitizenship()));
        }
        if (profile.getGender() != null){
            profileDto.setGender(genderMapper.toDto(profile.getGender()));
        }
        if (profile.getMaritalStatus() != null){
            profileDto.setMaritalStatus(maritalStatusMapper.toDto(profile.getMaritalStatus()));
        }
        return profileDto;
    }

    @Override
    public Profile toEntity(ProfileDto profileDto) {
        Profile profile = modelMapper.map(profileDto, Profile.class);
        if (profileDto.getNationality() != null){
            profile.setNationality(nationalityMapper.toEntity(profileDto.getNationality()));
        }
        if (profileDto.getCitizenship() != null){
            profile.setCitizenship(citizenshipMapper.toEntity(profileDto.getCitizenship()));
        }
        if (profileDto.getGender() != null){
            profile.setGender(genderMapper.toEntity(profileDto.getGender()));
        }
        if (profileDto.getMaritalStatus() != null){
            profile.setMaritalStatus(maritalStatusMapper.toEntity(profileDto.getMaritalStatus()));
        }
        return profile;
    }

    @Override
    public List<ProfileDto> toDtoList(List<Profile> profiles) {
        return profiles.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Profile> toEntityList(List<ProfileDto> profileDtos) {
        return profileDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
