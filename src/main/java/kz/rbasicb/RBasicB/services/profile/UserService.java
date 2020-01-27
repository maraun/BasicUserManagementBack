package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.User;

import java.util.List;

public interface UserService /*extends UserDetailsService*/ {

    User findById(Long id) throws ServiceException;
    List<User> findAll();
    List<User> findAllWithDeleted();
    User update(User user) throws ServiceException ;
    User save(User user) throws ServiceException ;
    void delete(User user) throws ServiceException ;
    void deleteById(Long id) throws ServiceException;
    /*    Set getAuthority(User user);*/
    User findByUsername(String username);
}
