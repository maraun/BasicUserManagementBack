package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.AdditionalDto;
import kz.rbasicb.RBasicB.models.entities.profile.Additional;
import kz.rbasicb.RBasicB.models.mappers.profile.AdditionalMapper;
import kz.rbasicb.RBasicB.services.profile.AdditionalService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/additional")
public class AdditionalController extends BaseController {

    private AdditionalService additionalService;
    private AdditionalMapper additionalMapper;

    @Autowired
    public AdditionalController(AdditionalService additionalService, AdditionalMapper additionalMapper) {
        this.additionalService = additionalService;
        this.additionalMapper = additionalMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(additionalMapper.toDtoList(additionalService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(additionalMapper.toDto(additionalService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody AdditionalDto additionalDto) throws ServiceException{
        Additional additional = additionalMapper.toEntity(additionalDto);
        additional = additionalService.save(additional);
        return buildResponse(additionalMapper.toDto(additional),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody AdditionalDto additionalDto) throws ServiceException{
        additionalService.deleteById((additionalMapper.toEntity(additionalDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody AdditionalDto additionalDto) throws ServiceException {
        Additional additional = additionalService.update(additionalMapper.toEntity(additionalDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(additionalMapper.toDto(additional))
                .build(), HttpStatus.OK);
    }



}