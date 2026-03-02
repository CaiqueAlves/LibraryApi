package io.github.caiquealves.libraryapi.service;

import io.github.caiquealves.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.repository.AutorRepository;
import io.github.caiquealves.libraryapi.repository.LivroRepository;
import io.github.caiquealves.libraryapi.validador.AutorValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {
    private final AutorRepository repository;
    private final AutorValidator validator;
    private final LivroRepository livroRepository;

    public AutorService(AutorRepository repository, AutorValidator validator, LivroRepository livroRepository) {
        this.repository = repository;
        this.validator = validator;
        this.livroRepository = livroRepository;
    }

    public Autor salvar(Autor autor) {
        validator.validar(autor);
        return repository.save(autor);
    }

    public void atualizar(Autor autor) {
        if (autor.getId()== null) {
            throw new IllegalArgumentException("Necessario informar o autor cadastrado na base de dados");
        }
        validator.validar(autor);
        repository.save(autor);
    }

    public Optional<Autor> obterPorId(UUID id) {
        return repository.findById(id);
    }

    public void deletar(Autor autor) {
        if(possuiLivro(autor)){
            throw new OperacaoNaoPermitidaException("Autor possui livro cadastrado");
        }
        repository.delete(autor);
    }

    public List <Autor> pesquisar(String nome, String nacionalidade){
        if(nome != null && nacionalidade != null){
            return repository.findByNomeAndNacionalidade(nome, nacionalidade);
        } else if (nome != null) {
            return repository.findByNome(nome);
        }else if (nacionalidade != null){
            return repository.findByNacionalidade(nacionalidade);
        }
        return repository.findAll();

    }

    public boolean possuiLivro(Autor autor){
        return livroRepository.existsByAutor(autor);
    }
}
