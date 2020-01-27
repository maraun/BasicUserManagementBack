package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long> {
    Optional<MaritalStatus> findByDeletedAtIsNullAndId(Long id);
    List<MaritalStatus> findAllByDeletedAtIsNull();
}