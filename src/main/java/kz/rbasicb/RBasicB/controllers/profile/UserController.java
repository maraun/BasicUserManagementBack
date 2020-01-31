package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.UserDto;
import kz.rbasicb.RBasicB.models.entities.profile.User;
import kz.rbasicb.RBasicB.models.mappers.profile.UserMapper;
import kz.rbasicb.RBasicB.services.profile.UserService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/users")
public class UserController extends BaseController {

    private UserService userService;
    private UserMapper userMapper;


    @Autowired
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping
    @CrossOrigin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> getAll() {
        return buildResponse(userMapper.toDtoList(userService.findAll()), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(userMapper.toDto(userService.findById(id)), HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("pid/{id}")
    public ResponseEntity<?> getOneByProfileId(@PathVariable Long id) throws ServiceException {
        return buildResponse(userMapper.toDto(userService.findByProfileId(id)), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<?> add(@RequestBody UserDto userDto) throws ServiceException {
        User user = userService.save(userMapper.toEntity(userDto));
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
    }

/*    @PostMapping("/validate")
    public ResponseEntity<?> validate(@RequestParam String login) throws ServiceException {
        User user = userService.findByLogin(login);
        if (user != null) {
            throw ServiceException.builder().message("Login exists").errorCode(ErrorCode.ALREADY_EXISTS).build();
        } else {
            return buildResponse(SuccessResponse.builder().message("OK").build(), HttpStatus.OK);
        }

    }*/

@CrossOrigin
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws ServiceException {
        userService.deleteById(id);
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }

/*    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody UserDto userDto) throws ServiceException {
        userService.delete(userMapper.toEntity(userDto));
        return buildResponse(SuccessResponse.builder().message("deleted").build(), HttpStatus.OK);
    }*/
    @CrossOrigin
    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody UserDto userDto) throws ServiceException {
        User user = userService.update(userMapper.toEntity(userDto));
        return buildResponse(userMapper.toDto(user), HttpStatus.OK);
/*        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(userMapper.toDto(user))
                .build(), HttpStatus.OK);*/
    }

    @CrossOrigin
    @GetMapping("/current")
    public ResponseEntity<?> getCurrentUser(Authentication authentication) throws ServiceException {
        String username = authentication.getName();
        return buildResponse(userMapper.toDto(userService.findByUsername(username)), HttpStatus.OK);
        /*return buildResponse(SuccessResponse.builder()
                .message("found")
                .data(userMapper.toDto(user))
                .build(), HttpStatus.OK);*/
    }

}
