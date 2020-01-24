package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDto extends BaseDto {
    private String username;
    private String password;
    private Set<RoleDto> roles = new HashSet<>();
    private ProfileDto profile;
    private Set<DocumentDto> documents = new HashSet<>();
    private Set<PositionDto> positions = new HashSet<>();
    private ContactsDto contacts;
    private AdditionalDto additional;
}
