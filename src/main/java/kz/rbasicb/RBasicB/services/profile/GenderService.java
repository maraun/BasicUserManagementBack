package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Gender;

import java.util.List;

public interface GenderService {
    Gender  findById(Long id) throws ServiceException;
    List<Gender> findAll();
    void deleteById(Long id) throws ServiceException;
    Gender  save(Gender gender) throws ServiceException;
    Gender update(Gender gender) throws ServiceException;
}
