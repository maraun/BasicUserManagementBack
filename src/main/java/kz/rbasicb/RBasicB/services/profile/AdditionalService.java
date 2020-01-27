package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Additional;

import java.util.List;

public interface AdditionalService {
    Additional findById(Long id) throws ServiceException;
    List<Additional> findAll();
    void deleteById(Long id) throws ServiceException;
    Additional save(Additional additional) throws ServiceException;
    Additional update(Additional additional) throws ServiceException;
}
