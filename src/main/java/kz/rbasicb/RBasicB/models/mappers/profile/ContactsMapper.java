package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.ContactsDto;
import kz.rbasicb.RBasicB.models.entities.profile.Contacts;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ContactsMapper extends AbstractModelMapper<Contacts, ContactsDto> {

    private ModelMapper modelMapper;
    @Autowired
    public ContactsMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public ContactsDto toDto(Contacts contacts) {
        return modelMapper.map(contacts, ContactsDto.class);
    }

    @Override
    public Contacts toEntity(ContactsDto contactsDto) {
        return modelMapper.map(contactsDto, Contacts.class);
    }

    @Override
    public List<ContactsDto> toDtoList(List<Contacts> contacts) {
        return contacts.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Contacts> toEntityList(List<ContactsDto> contactsDtos) {
        return contactsDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
