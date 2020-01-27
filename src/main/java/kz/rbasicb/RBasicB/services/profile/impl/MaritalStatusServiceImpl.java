package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.MaritalStatus;
import kz.rbasicb.RBasicB.repositories.profile.MaritalStatusRepository;
import kz.rbasicb.RBasicB.services.profile.MaritalStatusService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class MaritalStatusServiceImpl implements MaritalStatusService {
    private MaritalStatusRepository maritalStatusRepository;
    @Autowired
    public MaritalStatusServiceImpl(MaritalStatusRepository maritalStatusRepository) {
        this.maritalStatusRepository = maritalStatusRepository;
    }

    @Override
    public MaritalStatus findById(Long id) throws ServiceException {
        Optional<MaritalStatus> maritalStatusOptional = maritalStatusRepository.findByDeletedAtIsNullAndId(id);
        return maritalStatusOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("MaritalStatus information not found")
                .build());
    }

    @Override
    public List<MaritalStatus> findAll() {
        return maritalStatusRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        MaritalStatus maritalStatus = findById(id);
        maritalStatus.setDeletedAt(new Date());
        maritalStatusRepository.save(maritalStatus);
    }

    @Override
    public MaritalStatus save(MaritalStatus maritalStatus) throws ServiceException {
        if (maritalStatus.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("MaritalStatus information is already exists")
                    .build();
        }
        return maritalStatusRepository.save(maritalStatus);
    }

    @Override
    public MaritalStatus update(MaritalStatus maritalStatus) throws ServiceException {
        if (maritalStatus.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("MaritalStatus information is null")
                    .build();
        }
        return maritalStatusRepository.save(maritalStatus);
    }
}
