package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.GroupRoles;

import java.util.List;

public interface GroupRolesService {
    GroupRoles findById(Long id) throws ServiceException;
    List<GroupRoles> findAll();
    void deleteById(Long id) throws ServiceException;
    GroupRoles save(GroupRoles groupRoles) throws ServiceException;
    GroupRoles update(GroupRoles groupRoles) throws ServiceException;
}
