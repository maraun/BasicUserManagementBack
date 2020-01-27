package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Nationality;

import java.util.List;

public interface NationalityService {
    Nationality findById(Long id) throws ServiceException;
    List<Nationality> findAll();
    void deleteById(Long id) throws ServiceException;
    Nationality save(Nationality nationality) throws ServiceException;
    Nationality update(Nationality nationality) throws ServiceException;
}
