package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Additional;
import kz.rbasicb.RBasicB.repositories.profile.AdditionalRepository;
import kz.rbasicb.RBasicB.services.profile.AdditionalService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AdditionalServiceImpl implements AdditionalService {
    private AdditionalRepository additionalRepository;
    @Autowired
    public AdditionalServiceImpl(AdditionalRepository additionalRepository) {
        this.additionalRepository = additionalRepository;
    }

    @Override
    public Additional findById(Long id) throws ServiceException {
        Optional<Additional> additionalOptional = additionalRepository.findByDeletedAtIsNullAndId(id);
        return additionalOptional.orElseThrow(()->ServiceException.builder()
                                    .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                                    .message("Additional information not found")
                                    .build());
    }

    @Override
    public List<Additional> findAll() {
        return additionalRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Additional additional = findById(id);
        additional.setDeletedAt(new Date());
        additionalRepository.save(additional);
    }

    @Override
    public Additional save(Additional additional) throws ServiceException {
        if (additional.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Additional information is already exists")
                    .build();
        }
        return additionalRepository.save(additional);
    }

    @Override
    public Additional update(Additional additional) throws ServiceException {
        if (additional.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Additional information is null")
                    .build();
        }
        return additionalRepository.save(additional);
    }
}
