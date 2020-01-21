package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "additional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Additional extends AuditModel {
    @Column(name="information")
    private String information;
}
