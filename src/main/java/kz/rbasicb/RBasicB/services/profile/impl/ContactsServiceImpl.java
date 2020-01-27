package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Contacts;
import kz.rbasicb.RBasicB.repositories.profile.ContactsRepository;
import kz.rbasicb.RBasicB.services.profile.ContactsService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ContactsServiceImpl implements ContactsService {
    private ContactsRepository contactsRepository;
    @Autowired
    public ContactsServiceImpl(ContactsRepository contactsRepository) {
        this.contactsRepository = contactsRepository;
    }

    @Override
    public Contacts findById(Long id) throws ServiceException {
        Optional<Contacts> contactsOptional = contactsRepository.findByDeletedAtIsNullAndId(id);
        return contactsOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Contacts information not found")
                .build());
    }

    @Override
    public List<Contacts> findAll() {
        return contactsRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Contacts contacts = findById(id);
        contacts.setDeletedAt(new Date());
        contactsRepository.save(contacts);
    }

    @Override
    public Contacts save(Contacts contacts) throws ServiceException {
        if (contacts.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Contacts information is already exists")
                    .build();
        }
        return contactsRepository.save(contacts);
    }

    @Override
    public Contacts update(Contacts contacts) throws ServiceException {
        if (contacts.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Contacts information is null")
                    .build();
        }
        return contactsRepository.save(contacts);
    }
}
