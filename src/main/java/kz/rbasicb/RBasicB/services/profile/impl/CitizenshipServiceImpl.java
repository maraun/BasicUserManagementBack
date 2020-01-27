package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.Citizenship;
import kz.rbasicb.RBasicB.repositories.profile.CitizenshipRepository;
import kz.rbasicb.RBasicB.services.profile.CitizenshipService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
public class CitizenshipServiceImpl implements CitizenshipService {
    private CitizenshipRepository citizenshipRepository;
    @Autowired
    public CitizenshipServiceImpl(CitizenshipRepository citizenshipRepository) {
        this.citizenshipRepository = citizenshipRepository;
    }

    @Override
    public Citizenship findById(Long id) throws ServiceException {
        Optional<Citizenship> citizenshipOptional = citizenshipRepository.findByDeletedAtIsNullAndId(id);
        return citizenshipOptional.orElseThrow(()->ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Citizenship information not found")
                .build());
    }

    @Override
    public List<Citizenship> findAll() {
        return citizenshipRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        Citizenship citizenship = findById(id);
        citizenship.setDeletedAt(new Date());
        citizenshipRepository.save(citizenship);
    }

    @Override
    public Citizenship save(Citizenship citizenship) throws ServiceException {
        if (citizenship.getId() != null){
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Citizenship information is already exists")
                    .build();
        }
        return citizenshipRepository.save(citizenship);
    }

    @Override
    public Citizenship update(Citizenship citizenship) throws ServiceException {
        if (citizenship.getId() == null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Citizenship information is null")
                    .build();
        }
        return citizenshipRepository.save(citizenship);
    }
}
