package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "positions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position extends AuditModel {
    @Column(name="starttime")
    private Date starttime = new Date();

    @Column(name="finishtime")
    private Date finishtime = new Date();

    @Column(name="organization")
    private String organization;

    @Column(name="position")
    private String position;

}
