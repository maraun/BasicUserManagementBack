package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.GroupRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GroupRolesRepository extends JpaRepository<GroupRoles, Long> {
    Optional<GroupRoles> findByDeletedAtIsNullAndId(Long id);
    List<GroupRoles> findAllByDeletedAtIsNull();
}
