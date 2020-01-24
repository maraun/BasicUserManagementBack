package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NationalityDto extends BaseDto {
    private String name;
}
