package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.Nationality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface NationalityRepository extends JpaRepository<Nationality, Long> {
    Optional<Nationality> findByDeletedAtIsNullAndId(Long id);
    List<Nationality> findAllByDeletedAtIsNull();
}