package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Citizenship;

import java.util.List;

public interface CitizenshipService {
    Citizenship findById(Long id) throws ServiceException;
    List<Citizenship> findAll();
    void deleteById(Long id) throws ServiceException;
    Citizenship save(Citizenship citizenship) throws ServiceException;
    Citizenship update(Citizenship citizenship) throws ServiceException;
}
