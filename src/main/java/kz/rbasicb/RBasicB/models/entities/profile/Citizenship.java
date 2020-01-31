package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "citizenship")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Citizenship extends AuditModel {
    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    @Transient
    private CitizenshipName name;
}
