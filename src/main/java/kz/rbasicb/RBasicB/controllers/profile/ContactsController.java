package kz.rbasicb.RBasicB.controllers.profile;

import kz.rbasicb.RBasicB.controllers.BaseController;
import kz.rbasicb.RBasicB.exceptions.ServiceException;
import kz.rbasicb.RBasicB.models.dtos.profile.ContactsDto;
import kz.rbasicb.RBasicB.models.entities.profile.Contacts;
import kz.rbasicb.RBasicB.models.mappers.profile.ContactsMapper;
import kz.rbasicb.RBasicB.services.profile.ContactsService;
import kz.rbasicb.RBasicB.shared.utils.responses.SuccessResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile/contacts")
public class ContactsController extends BaseController {

    private ContactsService contactsService;
    private ContactsMapper contactsMapper;

    @Autowired
    public ContactsController(ContactsService contactsService, ContactsMapper contactsMapper) {
        this.contactsService = contactsService;
        this.contactsMapper = contactsMapper;
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        return buildResponse(contactsMapper.toDtoList(contactsService.findAll()), HttpStatus.OK);
    }


    @GetMapping("{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id) throws ServiceException {
        return buildResponse(contactsMapper.toDto(contactsService.findById(id)), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> add(@RequestBody ContactsDto contactsDto) throws ServiceException{
        Contacts contacts = contactsMapper.toEntity(contactsDto);
        contacts = contactsService.save(contacts);
        return buildResponse(contactsMapper.toDto(contacts),HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete (@RequestBody ContactsDto contactsDto) throws ServiceException{
        contactsService.deleteById((contactsMapper.toEntity(contactsDto)).getId());
        return buildResponse(SuccessResponse.builder().message("deleted").build(),HttpStatus.OK);
    }

    @RequestMapping(method = {RequestMethod.PATCH, RequestMethod.PUT})
    public ResponseEntity<?> update(@RequestBody ContactsDto contactsDto) throws ServiceException {
        Contacts contacts = contactsService.update(contactsMapper.toEntity(contactsDto));
        return buildResponse(SuccessResponse.builder()
                .message("updated")
                .data(contactsMapper.toDto(contacts))
                .build(), HttpStatus.OK);
    }



}