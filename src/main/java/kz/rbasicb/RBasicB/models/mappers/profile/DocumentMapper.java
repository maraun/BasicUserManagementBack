package kz.rbasicb.RBasicB.models.mappers.profile;

import kz.rbasicb.RBasicB.models.dtos.profile.DocumentDto;
import kz.rbasicb.RBasicB.models.entities.profile.Document;
import kz.rbasicb.RBasicB.shared.utils.mappers.AbstractModelMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class DocumentMapper extends AbstractModelMapper<Document, DocumentDto> {

    private ModelMapper modelMapper;
    @Autowired
    public DocumentMapper(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }
    @Override
    public DocumentDto toDto(Document document) {
        return modelMapper.map(document, DocumentDto.class);
    }

    @Override
    public Document toEntity(DocumentDto documentDto) {
        return modelMapper.map(documentDto, Document.class);
    }

    @Override
    public List<DocumentDto> toDtoList(List<Document> documents) {
        return documents.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Document> toEntityList(List<DocumentDto> documentDtos) {
        return documentDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
    public Set<DocumentDto> toDtoSet(Set<Document> documents) {
        return documents.stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    public Set<Document> toEntitySet(Set<DocumentDto> documentDtos) {
        return documentDtos.stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

}
