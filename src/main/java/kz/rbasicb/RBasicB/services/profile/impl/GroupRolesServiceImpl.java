package kz.rbasicb.RBasicB.services.profile.impl;

import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.entities.profile.GroupRoles;
import kz.rbasicb.RBasicB.repositories.profile.GroupRolesRepository;
import kz.rbasicb.RBasicB.services.profile.GroupRolesService;
import kz.rbasicb.RBasicB.shared.utils.codes.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class GroupRolesServiceImpl implements GroupRolesService {
    private GroupRolesRepository groupRolesRepository;
    @Autowired
    public GroupRolesServiceImpl(GroupRolesRepository groupRolesRepository) {
        this.groupRolesRepository = groupRolesRepository;
    }
    @Override
    public GroupRoles findById(Long id) throws ServiceException {
        Optional<GroupRoles> groupRolesOptional = groupRolesRepository.findByDeletedAtIsNullAndId(id);
        return groupRolesOptional.orElseThrow(()-> ServiceException.builder()
                .errorCode(ErrorCode.RESOURCE_NOT_FOUND)
                .message("Group Roles not found")
                .build());
    }

    @Override
    public List<GroupRoles> findAll() {
        return groupRolesRepository.findAllByDeletedAtIsNull();
    }

    @Override
    public void deleteById(Long id) throws ServiceException {
        if (id==null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.SYSTEM_ERROR)
                    .message("Id is null")
                    .build();
        }
        GroupRoles groupRoles = findById(id);
        groupRoles.setDeletedAt(new Date());
        groupRolesRepository.save(groupRoles);
    }

    @Override
    public GroupRoles save(GroupRoles groupRoles) throws ServiceException {
        if (groupRoles.getId()!=null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("This group is already exists")
                    .build();
        }
        return groupRolesRepository.save(groupRoles);
    }

    @Override
    public GroupRoles update(GroupRoles groupRoles) throws ServiceException {
        if (groupRoles.getId()==null) {
            throw ServiceException.builder()
                    .errorCode(ErrorCode.ALREADY_EXISTS)
                    .message("Group information is null")
                    .build();
        }
        return groupRolesRepository.save(groupRoles);
    }
}
