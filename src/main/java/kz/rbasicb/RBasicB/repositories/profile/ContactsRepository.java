package kz.rbasicb.RBasicB.repositories.profile;

import kz.rbasicb.RBasicB.models.entities.profile.Contacts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    Optional<Contacts> findByDeletedAtIsNullAndId(Long id);
    List<Contacts> findAllByDeletedAtIsNull();
}