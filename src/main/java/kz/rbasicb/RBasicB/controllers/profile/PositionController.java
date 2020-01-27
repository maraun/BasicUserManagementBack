package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.PositionDto;
import kz.rbasicb.RBasicB.models.entities.profile.Position;
import kz.rbasicb.RBasicB.models.mappers.profile.PositionMapper;
import kz.rbasicb.RBasicB.services.profile.PositionService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/position")
public class PositionController extends BaseController {

    private PositionService positionService;
    private PositionMapper positionMapper;

    @Autowired
    public PositionController(PositionService positionService, PositionMapper positionMapper) {
        this.positionService = positionService;
        this.positionMapper = positionMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(positionMapper.toDtoList(positionService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(positionMapper.toDto(positionService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody PositionDto positionDto) throws ServiceException{
        Position position = positionMapper.toEntity(positionDto);
        position = positionService.save(position);
        return buildResponse(positionMapper.toDto(position),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody PositionDto positionDto) throws ServiceException{
        positionService.deleteById((positionMapper.toEntity(positionDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody PositionDto positionDto) throws ServiceException {
        Position position = positionService.update(positionMapper.toEntity(positionDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(positionMapper.toDto(position))
                .build(), HttpStatus.OK);
    }



}