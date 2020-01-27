package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Profile;

import java.util.List;

public interface ProfileService {
    Profile findById(Long id) throws ServiceException;
    List<Profile> findAll();
    void deleteById(Long id) throws ServiceException;
    Profile save(Profile profile) throws ServiceException;
    Profile update(Profile profile) throws ServiceException;
}
