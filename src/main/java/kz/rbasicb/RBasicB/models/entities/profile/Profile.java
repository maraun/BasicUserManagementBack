package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Indexed
/*@Builder*/
public class Profile extends AuditModel {
    @Field
    @Column(name="firstname")
    private String firstname;
    @Field
    @Column(name="lastname")
    private String lastname;

    @Column(name="middlename")
    private String middlename;

    @Column(name = "previous_lastname")
    private String previouslastname;
    @Field
    @Column(name = "iin")
    private String iin;

    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToOne
    private Nationality nationality;

    @ManyToOne
    private Citizenship citizenship;

    @ManyToOne
    private Gender gender;

    @ManyToOne
    private MaritalStatus maritalStatus;

    @Column(name = "registration_place")
    private String registrationPlace;

    @Column(name = "living_place")
    private String livingPlace;

    @Column(name = "photo_name")
    private String photoname;
}
