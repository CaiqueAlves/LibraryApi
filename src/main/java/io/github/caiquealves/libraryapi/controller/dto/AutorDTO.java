package io.github.caiquealves.libraryapi.controller.dto;

import io.github.caiquealves.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(
        UUID id,

        @NotBlank(message = "Campo obrigatorio")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nome,

        @NotNull(message = "Campo obrigatorio")
        @Past(message = "Não pode ser uma data futura")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatorio")
        @Size(min = 2, max = 100, message = "Campo fora do tamanho padrão")
        String nacionalidade) {


    public Autor mapearPorAutor() {
        Autor autor = new Autor();
        autor.setNome(nome);
        autor.setDataNascimento(dataNascimento);
        autor.setNacionalidade(nacionalidade);
        return autor;
    }
}
