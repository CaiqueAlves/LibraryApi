package io.github.caiquealves.libraryapi.service;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.repository.AutorRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
public class AutorService {
    private final AutorRepository Repository;

    public AutorService(AutorRepository repository) {
        Repository = repository;
    }

    public Autor salvar(Autor autor) {
        return Repository.save(autor);
    }
}
