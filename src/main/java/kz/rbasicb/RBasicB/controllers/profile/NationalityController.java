package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.NationalityDto;
import kz.rbasicb.RBasicB.models.entities.profile.Nationality;
import kz.rbasicb.RBasicB.models.mappers.profile.NationalityMapper;
import kz.rbasicb.RBasicB.services.profile.NationalityService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/nationality")
public class NationalityController extends BaseController {

    private NationalityService nationalityService;
    private NationalityMapper nationalityMapper;

    @Autowired
    public NationalityController(NationalityService nationalityService, NationalityMapper nationalityMapper) {
        this.nationalityService = nationalityService;
        this.nationalityMapper = nationalityMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(nationalityMapper.toDtoList(nationalityService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(nationalityMapper.toDto(nationalityService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody NationalityDto nationalityDto) throws ServiceException{
        Nationality nationality = nationalityMapper.toEntity(nationalityDto);
        nationality = nationalityService.save(nationality);
        return buildResponse(nationalityMapper.toDto(nationality),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody NationalityDto nationalityDto) throws ServiceException{
        nationalityService.deleteById((nationalityMapper.toEntity(nationalityDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody NationalityDto nationalityDto) throws ServiceException {
        Nationality nationality = nationalityService.update(nationalityMapper.toEntity(nationalityDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(nationalityMapper.toDto(nationality))
                .build(), HttpStatus.OK);
    }



}