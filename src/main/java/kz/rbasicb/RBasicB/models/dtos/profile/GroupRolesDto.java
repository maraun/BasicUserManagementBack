package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import kz.rbasicb.RBasicB.models.entities.profile.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class GroupRolesDto extends BaseDto {
    private String name;
    private Set<Role> roles = new HashSet<>();
}
