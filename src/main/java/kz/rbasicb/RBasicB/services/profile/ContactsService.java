package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Contacts;

import java.util.List;

public interface ContactsService {
    Contacts findById(Long id) throws ServiceException;
    List<Contacts> findAll();
    void deleteById(Long id) throws ServiceException;
    Contacts save(Contacts contacts) throws ServiceException;
    Contacts update(Contacts contacts) throws ServiceException;
}
