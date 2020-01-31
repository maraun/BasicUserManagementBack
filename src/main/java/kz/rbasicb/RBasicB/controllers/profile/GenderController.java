package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.GenderDto;
import kz.rbasicb.RBasicB.models.entities.profile.Gender;
import kz.rbasicb.RBasicB.models.mappers.profile.GenderMapper;
import kz.rbasicb.RBasicB.services.profile.GenderService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/gender")
public class GenderController extends BaseController {

    private GenderService genderService;
    private GenderMapper genderMapper;

    @Autowired
    public GenderController(GenderService genderService, GenderMapper genderMapper) {
        this.genderService = genderService;
        this.genderMapper = genderMapper;
    }
    @CrossOrigin
    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(genderMapper.toDtoList(genderService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(genderMapper.toDto(genderService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody GenderDto genderDto) throws ServiceException{
        Gender gender = genderMapper.toEntity(genderDto);
        gender = genderService.save(gender);
        return buildResponse(genderMapper.toDto(gender),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody GenderDto genderDto) throws ServiceException{
        genderService.deleteById((genderMapper.toEntity(genderDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody GenderDto genderDto) throws ServiceException {
        Gender gender = genderService.update(genderMapper.toEntity(genderDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(genderMapper.toDto(gender))
                .build(), HttpStatus.OK);
    }



}