package kz.rbasicb.RBasicB.models.dtos.profile;

import kz.rbasicb.RBasicB.models.dtos.base.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto extends BaseDto {
    private String firstname;

    private String lastname;

    private String middlename;

    private String previouslastname;

    private String iin;

    private Date birthdate;

    private NationalityDto nationality;

    private CitizenshipDto citizenship;

    private GenderDto gender;

    private MaritalStatusDto maritalStatus;

    private String registrationPlace;

    private String livingPlace;

    private String photoname;
}
