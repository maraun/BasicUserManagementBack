package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import kz.rbasicb.RBasicB.models.entities.profile.DocumentType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.Date;

@Data
@NoArgsConstructor
public class DocumentDto extends BaseDto {
    private String type;

    private String identityNumber;

    private String serialNumber;

    private Date issuedDate;

    private Date validityDate;

    private String issuedPlace;
}
