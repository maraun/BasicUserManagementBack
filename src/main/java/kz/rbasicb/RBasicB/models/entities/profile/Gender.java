package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "genders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gender extends AuditModel {

    @Column(length = 60)
    private String name;
}
