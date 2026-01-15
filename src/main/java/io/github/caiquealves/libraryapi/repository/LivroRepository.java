package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.model.GeneroLivro;
import io.github.caiquealves.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {

    //query method
    //sect * from livro where id_autor = id
    List<Livro>findByAutor(Autor autor);

    Livro findByTitulo(String titulo);

    List<Livro>findByIsbn(String isbn);

    List<Livro>findByTituloAndPreco(String livro, BigDecimal preco);

    @Query("select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> ListarTodosOrdenadoPorTituloEPreco();

    @Query("select a from Livro l join l.autor a ")
    List<Autor> ListarAutoresDosLivros();

    @Query("select distinct l.titulo from Livro l")
    List<String> listarNomesDiferentesLivros();

    @Query("""
           select l.genero
           from Livro l
           join l.autor a
           where a.nacionalidade = 'Brasileira'
           order by l.genero  
    """)
    List<String> ListarGenerosAutoresBrasileiros();

    @Query("select l from Livro l where l.genero = :genero order by :paramOrdenacao")
    List<Livro> findByGenero(
            @Param("genero")GeneroLivro generoLivro,
            @Param("paramOrdenacao") String nomeParam
    );

    @Query("select l from Livro l where l.genero = ?1 order by =?")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro, String nomePropriedade);

}
