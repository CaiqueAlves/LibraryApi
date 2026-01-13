package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.model.GeneroLivro;
import io.github.caiquealves.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));
        autorRepository.save(autor);

        var autorSalvo = autorRepository.save(autor);
        System.out.println( "Autor salvo"+autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("77c1e3f0-823c-4845-9ff5-88ec09617abe");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado =  possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1967, 1, 30));

            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista= autorRepository.findAll();
        lista.forEach(System.out::println);
    }
    @Test
    public void countTest(){
        System.out.println("Contagem de atores: " + autorRepository.count());
    }

    @Test
    public void deletarPorIdTest(){
        var id = UUID.fromString("77c1e3f0-823c-4845-9ff5-88ec09617abe");
        autorRepository.deleteById(id);
    }

    @Test
    public void deletarTest(){
        var id = UUID.fromString("fe5b03bb-1f1b-42ea-9e83-36fbfcac715f");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    public void salvarAutorComLivro(){
        Autor autor = new Autor();
        autor.setNome("Antonio");
        autor.setNacionalidade("Americano");
        autor.setDataNascimento(LocalDate.of(1970, 8, 5));

        Livro livro = new Livro();
        livro.setIsbn("15153-32165");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.MISTERIO);
        livro.setTitulo("O roubo da casa assombrada");
        livro.setDataPublicacao(LocalDate.of(1999, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("15155-32165");
        livro2.setPreco(BigDecimal.valueOf(204));
        livro2.setGenero(GeneroLivro.MISTERIO);
        livro2.setTitulo("O roubo do banco assombrado");
        livro2.setDataPublicacao(LocalDate.of(2000, 1, 2));
        livro2.setAutor(autor);

        autor.setLivos(new  ArrayList<>());
        autor.getLivos().add(livro);
        autor.getLivos().add(livro2);

        autorRepository.save(autor);
       //livroRepository.saveAll(autor.getLivos());
    }

    @Test
    public void listarLivrosAutor(){
        var id = UUID.fromString("7e0c6e46-f9ff-49a3-9727-baafe2ab94bc");
        var autor = autorRepository.findById(id).get();

        //buscar livros do autor
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivos(livrosLista);

        autor.getLivos().forEach(System.out::println);
    }
}
