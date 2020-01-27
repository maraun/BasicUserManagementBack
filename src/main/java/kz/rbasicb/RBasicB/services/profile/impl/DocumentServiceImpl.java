package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Document;
import kz.rbasicb.RBasicB.repositories.profile.DocumentRepository;
import kz.rbasicb.RBasicB.services.profile.DocumentService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class DocumentServiceImpl implements DocumentService {
    private DocumentRepository documentRepository;
    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document findById(Long id) throws ServiceException {
        Optional<Document> documentOptional = documentRepository.findByDeletedAtIsNullAndId(id);
        return documentOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Document information not found")
                .build());
    }

    @Override
    public List<Document> findAll() {
        return documentRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Document document = findById(id);
        document.setDeletedAt(new Date());
        documentRepository.save(document);
    }

    @Override
    public Document save(Document document) throws ServiceException {
        if (document.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Document information is already exists")
                    .build();
        }
        return documentRepository.save(document);
    }

    @Override
    public Document update(Document document) throws ServiceException {
        if (document.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Document information is null")
                    .build();
        }
        return documentRepository.save(document);
    }
}
