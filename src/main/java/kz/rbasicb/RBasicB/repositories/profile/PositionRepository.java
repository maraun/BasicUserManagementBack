package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    Optional<Position> findByDeletedAtIsNullAndId(Long id);
    List<Position> findAllByDeletedAtIsNull();
}