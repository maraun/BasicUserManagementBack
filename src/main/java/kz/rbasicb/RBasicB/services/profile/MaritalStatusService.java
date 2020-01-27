package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.MaritalStatus;

import java.util.List;

public interface MaritalStatusService {
    MaritalStatus findById(Long id) throws ServiceException;
    List<MaritalStatus> findAll();
    void deleteById(Long id) throws ServiceException;
    MaritalStatus save() throws ServiceException;
    MaritalStatus update() throws ServiceException;
}
