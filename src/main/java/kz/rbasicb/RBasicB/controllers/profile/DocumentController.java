package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.DocumentDto;
import kz.rbasicb.RBasicB.models.entities.profile.Document;
import kz.rbasicb.RBasicB.models.mappers.profile.DocumentMapper;
import kz.rbasicb.RBasicB.services.profile.DocumentService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/document")
public class DocumentController extends BaseController {

    private DocumentService documentService;
    private DocumentMapper documentMapper;

    @Autowired
    public DocumentController(DocumentService documentService, DocumentMapper documentMapper) {
        this.documentService = documentService;
        this.documentMapper = documentMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(documentMapper.toDtoList(documentService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(documentMapper.toDto(documentService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody DocumentDto documentDto) throws ServiceException{
        Document document = documentMapper.toEntity(documentDto);
        document = documentService.save(document);
        return buildResponse(documentMapper.toDto(document),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody DocumentDto documentDto) throws ServiceException{
        documentService.deleteById((documentMapper.toEntity(documentDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody DocumentDto documentDto) throws ServiceException {
        Document document = documentService.update(documentMapper.toEntity(documentDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(documentMapper.toDto(document))
                .build(), HttpStatus.OK);
    }



}