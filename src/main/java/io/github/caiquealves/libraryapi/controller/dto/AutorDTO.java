package io.github.caiquealves.libraryapi.controller.dto;

import io.github.caiquealves.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatorio")
        String nome,
        @NotNull(message = "Campo obrigatorio")
        LocalDate dataNascimento,
        @NotBlank(message = "Campo obrigatorio")
        String nacionalidade) {


    public Autor mapearPorAutor() {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setDataNascimento(dataNascimento);
        autor.setNacionalidade(nacionalidade);
        return autor;
    }
}
