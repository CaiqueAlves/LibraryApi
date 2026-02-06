package io.github.caiquealves.libraryapi.service;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.repository.AutorRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository Repository;

    public AutorService(AutorRepository repository) {
        Repository = repository;
    }

    public Autor salvar(Autor autor) {
        return Repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return Repository.findById(id);
    }
}
