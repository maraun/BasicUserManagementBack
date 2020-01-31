package kz.rbasicb.RBasicB.models.entities.profile;

import kz.rbasicb.RBasicB.models.audits.AuditModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
@Data
@NoArgsConstructor
public class User extends AuditModel {

    @NotBlank
    @Size(min=3, max = 50)
    private String username;

    @NotBlank
    @Size(min=6, max = 100)
    private String password;

    @ManyToMany(
            fetch = FetchType.EAGER
                )
    @JoinTable(
            name = "user_roles",
            joinColumns =
                    {
                            @JoinColumn(
                                    name = "user_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_users")
                            )
                    },
            inverseJoinColumns =
                    {
                            @JoinColumn(
                                    name = "role_id",
                                    nullable = false,
                                    foreignKey = @ForeignKey(name = "fk_users_roles_roles")
                            )
                    }
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Document> documents = new HashSet<>();

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Position> positions = new HashSet<>();

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "contacts_id")
    private Contacts contacts;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Additional additional;
}
