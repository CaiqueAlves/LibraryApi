package io.github.caiquealves.libraryapi.validador;

import io.github.caiquealves.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.caiquealves.libraryapi.model.Livro;
import io.github.caiquealves.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class LivroValidador {

    private final LivroRepository repository;

    public void validar(Livro livro){
        if(existeLivroComIsbn(livro)){
            throw new RegistroDuplicadoException("ISBN ja cadastrado");
        }
    }

    private boolean existeLivroComIsbn(Livro livro){
        Optional<Livro> livroEncontrado = repository.findByIsbn(livro.getIsbn());

        if(livro.getId() == null){
            return livroEncontrado.isPresent();
        }

        return livroEncontrado
                    .map(Livro::getId)
                    .stream()
                    .anyMatch(id -> !id.equals(livro.getId()));
    }
}
