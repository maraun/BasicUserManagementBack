package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {
    Optional<Gender> findByDeletedAtIsNullAndId(Long id);
    List<Gender> findAllByDeletedAtIsNull();
}