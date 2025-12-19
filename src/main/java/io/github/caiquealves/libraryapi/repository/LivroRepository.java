package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.Model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
}
