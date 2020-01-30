package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.User;
import kz.rbasicb.RBasicB.repositories.profile.UserRepository;
import kz.rbasicb.RBasicB.services.profile.UserService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private SessionFactory hibernateFactory;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           EntityManagerFactory factory) {
        this.userRepository = userRepository;
        if (factory.unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("Factory is not a hibernate factory");
        }
        this.hibernateFactory = factory.unwrap(SessionFactory.class);

    }

    @Override
    public User findById(Long id) throws ServiceException {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("User not found")
                .build());
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public List<User> findAllWithDeleted() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("User is null")
                    .build();
        }
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        return  userRepository.save(user);
    }

    @Override
    public User save(User user) throws ServiceException {
        if(user.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("User is already exists")
                    .build();
        }
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        return  userRepository.save(user);
    }

    @Override
    public void delete(User user) throws ServiceException {
        if(user.getId() == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("User is null")
                    .build();
        }
        user = findById(user.getId());
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if(id == null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        User user = findById(id);
        user.setDeletedAt(new Date());
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        Session session = hibernateFactory.openSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
        Root<User> root = criteriaQuery.from(User.class);

        Predicate predicate1 = criteriaBuilder.isNull(root.get("deletedAt"));
        Predicate predicate2 = criteriaBuilder.equal(root.get("username"), username);
        Predicate andPredicate = criteriaBuilder.and(predicate1, predicate2);

        criteriaQuery.where(andPredicate);
        User user;
        try {
            user = session.createQuery(criteriaQuery).getSingleResult();
        } catch (Exception e) {
            user = null;
        }
        session.close();
        return user;
    }

    @Override
    public User findByProfileId(Long id) throws ServiceException {
        Optional<User> userOptional = userRepository.findByProfileId(id);
        return userOptional.orElseThrow(() -> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("User not found")
                .build());
    }

/*    @Override
    public Set getAuthority(User user) {
        Set authorities = new HashSet<>();
        for (Role role : user.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        *//*user.getRoles().forEach((r) -> {authorities.add(new SimpleGrantedAuthority(r.getName()));});*//**//*TODO*//*
        return authorities;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), getAuthority(user));
    }*/

}
