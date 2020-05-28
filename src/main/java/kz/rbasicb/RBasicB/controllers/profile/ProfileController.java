package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.ProfileDto;
import kz.rbasicb.RBasicB.models.entities.profile.Profile;
import kz.rbasicb.RBasicB.models.mappers.profile.ProfileMapper;
import kz.rbasicb.RBasicB.services.profile.ProfileService;
import kz.rbasicb.RBasicB.services.search.HibernateProfileSearchService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/profile")
public class ProfileController extends BaseController {

    private ProfileService profileService;
    private ProfileMapper profileMapper;
    @Autowired
    private HibernateProfileSearchService hibernateProfileSearchService;

    @Autowired
    public ProfileController(ProfileService profileService, ProfileMapper profileMapper) {
        this.profileService = profileService;
        this.profileMapper = profileMapper;
    }

    @GetMapping
    @CrossOrigin
    public ResponseEntity<?> getAll(){
        return buildResponse(profileMapper.toDtoList(profileService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(profileMapper.toDto(profileService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ProfileDto profileDto) throws ServiceException{
        Profile profile = profileMapper.toEntity(profileDto);
        profile = profileService.save(profile);
        return buildResponse(profileMapper.toDto(profile),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody ProfileDto profileDto) throws ServiceException{
        profileService.deleteById((profileMapper.toEntity(profileDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody ProfileDto profileDto) throws ServiceException {
        Profile profile = profileService.update(profileMapper.toEntity(profileDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(profileMapper.toDto(profile))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/find/{text}")
    @CrossOrigin
    public ResponseEntity<?> searchByKeyword(@PathVariable String text) throws ServiceException {
        return buildResponse(profileMapper.toDtoList(hibernateProfileSearchService.searchMultipleFieldsByKeywordQuery(text)), HttpStatus.OK);
    }


}