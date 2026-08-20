package io.github.caiquealves.libraryapi.controller.mappers;

import io.github.caiquealves.libraryapi.controller.dto.AutorDTO;
import io.github.caiquealves.libraryapi.model.Autor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AutorMapper {

    @Mapping(source="nome", target ="nome")
    @Mapping(source="dataNascimento", target ="dataNascimento")
    @Mapping(source="nacionalidade", target ="nacionalidade")
    Autor toEntity(AutorDTO autorDTO);

    AutorDTO toDTO(Autor autor);


}
