package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Nationality;
import kz.rbasicb.RBasicB.repositories.profile.NationalityRepository;
import kz.rbasicb.RBasicB.services.profile.NationalityService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class NationalityServiceImpl implements NationalityService {
    private NationalityRepository nationalityRepository;
    @Autowired
    public NationalityServiceImpl(NationalityRepository nationalityRepository) {
        this.nationalityRepository = nationalityRepository;
    }

    @Override
    public Nationality findById(Long id) throws ServiceException {
        Optional<Nationality> nationalityOptional = nationalityRepository.findByDeletedAtIsNullAndId(id);
        return nationalityOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Nationality information not found")
                .build());
    }

    @Override
    public List<Nationality> findAll() {
        return nationalityRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Nationality nationality = findById(id);
        nationality.setDeletedAt(new Date());
        nationalityRepository.save(nationality);
    }

    @Override
    public Nationality save(Nationality nationality) throws ServiceException {
        if (nationality.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Nationality information is already exists")
                    .build();
        }
        return nationalityRepository.save(nationality);
    }

    @Override
    public Nationality update(Nationality nationality) throws ServiceException {
        if (nationality.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Nationality information is null")
                    .build();
        }
        return nationalityRepository.save(nationality);
    }
}
