package io.github.caiquealves.libraryapi.controller.dto;

import io.github.caiquealves.libraryapi.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaDTO(UUID id,
                                   String isbn,
                                   String titulo,
                                   LocalDate dataPublicacao,
                                   GeneroLivro genero,
                                   BigDecimal preco,
                                   AutorDTO autor
){
}
