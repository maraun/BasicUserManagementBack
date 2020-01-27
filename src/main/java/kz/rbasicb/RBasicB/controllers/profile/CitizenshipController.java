package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.CitizenshipDto;
import kz.rbasicb.RBasicB.models.entities.profile.Citizenship;
import kz.rbasicb.RBasicB.models.mappers.profile.CitizenshipMapper;
import kz.rbasicb.RBasicB.services.profile.CitizenshipService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/citizenship")
public class CitizenshipController extends BaseController {

    private CitizenshipService citizenshipService;
    private CitizenshipMapper citizenshipMapper;

    @Autowired
    public CitizenshipController(CitizenshipService citizenshipService, CitizenshipMapper citizenshipMapper) {
        this.citizenshipService = citizenshipService;
        this.citizenshipMapper = citizenshipMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(citizenshipMapper.toDtoList(citizenshipService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(citizenshipMapper.toDto(citizenshipService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody CitizenshipDto citizenshipDto) throws ServiceException{
        Citizenship citizenship = citizenshipMapper.toEntity(citizenshipDto);
        citizenship = citizenshipService.save(citizenship);
        return buildResponse(citizenshipMapper.toDto(citizenship),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody CitizenshipDto citizenshipDto) throws ServiceException{
        citizenshipService.deleteById((citizenshipMapper.toEntity(citizenshipDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody CitizenshipDto citizenshipDto) throws ServiceException {
        Citizenship citizenship = citizenshipService.update(citizenshipMapper.toEntity(citizenshipDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(citizenshipMapper.toDto(citizenship))
                .build(), HttpStatus.OK);
    }



}