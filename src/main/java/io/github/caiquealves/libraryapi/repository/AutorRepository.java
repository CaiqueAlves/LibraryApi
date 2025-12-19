package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.Model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface AutorRepository extends JpaRepository <Autor, UUID> {

}
