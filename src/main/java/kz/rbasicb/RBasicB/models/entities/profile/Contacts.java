package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contacts extends AuditModel {
    @Column(name="workphone")
    private String workphone;

    @Column(name="homephone")
    private String homephone;

    @Column(name="mobilephone")
    private String mobilephone;

    @Column(name="email")
    private String email;
}
