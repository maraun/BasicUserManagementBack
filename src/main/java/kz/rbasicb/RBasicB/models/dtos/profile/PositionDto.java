package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
public class PositionDto extends BaseDto {
    private Date starttime = new Date();

    private Date finishtime = new Date();

    private String organization;

    private String position;
}
