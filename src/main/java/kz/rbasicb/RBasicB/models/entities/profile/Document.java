package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "documents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Document extends AuditModel {

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private DocumentType type;

    @Column(name = "identity_number")
    private String identityNumber;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "issued_date")
    private Date issuedDate;

    @Column(name = "validity_date")
    private Date validityDate;

    @Column(name = "issued_place")
    private String issuedPlace;
}
