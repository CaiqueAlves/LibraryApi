package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //query method
    //sect * from livro where id_autor = id
    List<Livro>findByAutor(Autor autor);
}
