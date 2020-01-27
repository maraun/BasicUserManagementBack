package kz.rbasicb.RBasicB.services.profile;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Document;

import java.util.List;

public interface DocumentService {
    Document findById(Long id) throws ServiceException;
    List<Document> findAll();
    void deleteById(Long id) throws ServiceException;
    Document save(Document document) throws ServiceException;
    Document update(Document document) throws ServiceException;
}
