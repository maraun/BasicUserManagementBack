package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.MaritalStatusDto;
import kz.rbasicb.RBasicB.models.entities.profile.MaritalStatus;
import kz.rbasicb.RBasicB.models.mappers.profile.MaritalStatusMapper;
import kz.rbasicb.RBasicB.services.profile.MaritalStatusService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/maritalStatus")
public class MaritalStatusController extends BaseController {

    private MaritalStatusService maritalStatusService;
    private MaritalStatusMapper maritalStatusMapper;

    @Autowired
    public MaritalStatusController(MaritalStatusService maritalStatusService, MaritalStatusMapper maritalStatusMapper) {
        this.maritalStatusService = maritalStatusService;
        this.maritalStatusMapper = maritalStatusMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(maritalStatusMapper.toDtoList(maritalStatusService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(maritalStatusMapper.toDto(maritalStatusService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody MaritalStatusDto maritalStatusDto) throws ServiceException{
        MaritalStatus maritalStatus = maritalStatusMapper.toEntity(maritalStatusDto);
        maritalStatus = maritalStatusService.save(maritalStatus);
        return buildResponse(maritalStatusMapper.toDto(maritalStatus),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody MaritalStatusDto maritalStatusDto) throws ServiceException{
        maritalStatusService.deleteById((maritalStatusMapper.toEntity(maritalStatusDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody MaritalStatusDto maritalStatusDto) throws ServiceException {
        MaritalStatus maritalStatus = maritalStatusService.update(maritalStatusMapper.toEntity(maritalStatusDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(maritalStatusMapper.toDto(maritalStatus))
                .build(), HttpStatus.OK);
    }



}