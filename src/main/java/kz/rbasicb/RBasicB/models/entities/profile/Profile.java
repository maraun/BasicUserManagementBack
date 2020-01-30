package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
/*@Builder*/
public class Profile extends AuditModel {

    @Column(name="firstname")
    private String firstname;

    @Column(name="lastname")
    private String lastname;

    @Column(name="middlename")
    private String middlename;

    @Column(name = "previous_lastname")
    private String previouslastname;

    @Column(name = "iin")
    private String iin;

    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Nationality nationality;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Citizenship citizenship;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Gender gender;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MaritalStatus maritalStatus;

    @Column(name = "registration_place")
    private String registrationPlace;

    @Column(name = "living_place")
    private String livingPlace;

    @Column(name = "photo_name")
    private String photoname;
}
