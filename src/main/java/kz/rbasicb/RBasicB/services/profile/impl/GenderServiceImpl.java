package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Gender;
import kz.rbasicb.RBasicB.repositories.profile.GenderRepository;
import kz.rbasicb.RBasicB.services.profile.GenderService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class GenderServiceImpl implements GenderService {
    private GenderRepository genderRepository;
    @Autowired
    public GenderServiceImpl(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    @Override
    public Gender findById(Long id) throws ServiceException {
        Optional<Gender> genderOptional = genderRepository.findByDeletedAtIsNullAndId(id);
        return genderOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Gender information not found")
                .build());
    }

    @Override
    public List<Gender> findAll() {
        return genderRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Gender gender = findById(id);
        gender.setDeletedAt(new Date());
        genderRepository.save(gender);
    }

    @Override
    public Gender save(Gender gender) throws ServiceException {
        if (gender.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Gender information is already exists")
                    .build();
        }
        return genderRepository.save(gender);
    }

    @Override
    public Gender update(Gender gender) throws ServiceException {
        if (gender.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Gender information is null")
                    .build();
        }
        return genderRepository.save(gender);
    }
}
