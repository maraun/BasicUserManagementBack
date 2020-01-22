package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AdditionalDto extends BaseDto {
    private String information;
}
