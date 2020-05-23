package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.GroupRolesDto;
import kz.rbasicb.RBasicB.models.entities.profile.GroupRoles;
import kz.rbasicb.RBasicB.models.mappers.profile.GroupRolesMapper;
import kz.rbasicb.RBasicB.services.profile.GroupRolesService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/grouproles")
public class GroupRolesController extends BaseController {
    private GroupRolesService groupRolesService;
    private GroupRolesMapper groupRolesMapper;

    public GroupRolesController(GroupRolesService groupRolesService, GroupRolesMapper groupRolesMapper) {
        this.groupRolesService = groupRolesService;
        this.groupRolesMapper = groupRolesMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(groupRolesMapper.toDtoList(groupRolesService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(groupRolesMapper.toDto(groupRolesService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody GroupRolesDto groupRolesDto) throws ServiceException{
        GroupRoles groupRoles = groupRolesMapper.toEntity(groupRolesDto);
        groupRoles = groupRolesService.save(groupRoles);
        return buildResponse(groupRolesMapper.toDto(groupRoles),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody GroupRolesDto groupRolesDto) throws ServiceException{
        groupRolesService.deleteById((groupRolesMapper.toEntity(groupRolesDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody GroupRolesDto groupRolesDto) throws ServiceException {
        GroupRoles groupRoles = groupRolesService.update(groupRolesMapper.toEntity(groupRolesDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(groupRolesMapper.toDto(groupRoles))
                .build(), HttpStatus.OK);
    }
}
