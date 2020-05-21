package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.mappers.profile.RoleMapper;
import kz.rbasicb.RBasicB.services.profile.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/roles")
public class RoleController extends BaseController {

    private RoleService roleService;
    private RoleMapper roleMapper;

    @Autowired
    public RoleController(RoleService roleService, RoleMapper roleMapper) {
        this.roleService = roleService;
        this.roleMapper = roleMapper;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> getAll(){
        return buildResponse(roleMapper.toDtoList(roleService.findAll()), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(roleMapper.toDto(roleService.findById(id)), HttpStatus.OK);
    }

}
