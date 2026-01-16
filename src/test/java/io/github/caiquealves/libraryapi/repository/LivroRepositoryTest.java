package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.model.GeneroLivro;
import io.github.caiquealves.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;

    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(357.99));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Vacinas o risco de apressar");
        livro.setDataPublicacao(LocalDate.of(2023, 2, 25));

        Autor autor = autorRepository
                .findById(UUID.fromString("2e89840d-40fa-46a4-a895-a134c3bea8ef"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarAutorELivroTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Vacinas o risco de apressar");
        livro.setDataPublicacao(LocalDate.of(2023, 03, 27));

        Autor autor = new Autor();
        autor.setNome("Vinicius aguiar");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1984, 5, 23));

        autorRepository.save(autor);

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void salvarCascadeTest(){
        Livro livro = new Livro();
        livro.setIsbn("90887-84875");
        livro.setPreco(BigDecimal.valueOf(250));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Vacinas, obrigatórias ou não");
        livro.setDataPublicacao(LocalDate.of(2023, 5, 23));

        Autor autor = new Autor();
        autor.setNome("Vinicius aguiar");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1984, 5, 23));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("f8c30e0c-31ed-4113-ba1e-6fc1acc49955");
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("208ea498-cb1d-442c-b402-6aa9ec193496");
        Autor maria = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(maria);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletar(){
        UUID id = UUID.fromString("f8c30e0c-31ed-4113-ba1e-6fc1acc49955");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascade(){
        UUID id = UUID.fromString("4e0ddc7f-0199-47ee-95d3-f4d57d6ea2e8");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTest(){
        UUID id = UUID.fromString("6adce4dd-ac8b-4c1d-95d4-bc492d3ba37a");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("livro: ");
        System.out.println(livro.getTitulo());
        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

    @Test
    void pesquisarPorTituloTest(){
        Livro livro = livroRepository.findByTitulo("O roubo da casa assombrada");
        System.out.println(livro);
    }

    @Test
    void pesquisarPorIsbnTest(){
        List<Livro> lista = livroRepository.findByIsbn("15153-32165");
        lista.forEach(System.out::println);
    }

    @Test
    void pesquisarPorTituloEPrecoTest(){
        var preco = BigDecimal.valueOf(204.00);
        List<Livro> lista = livroRepository.findByTituloAndPreco("O roubo do banco assombrado", preco);
        lista.forEach(System.out::println);
    }

    @Test
    void listarLivrosComQueryJPQL(){
        var resultado = livroRepository.ListarTodosOrdenadoPorTituloEPreco();
        resultado.forEach(System.out::println);
    }

    @Test
    void ListarAutoresDosLivros(){
        var resultado = livroRepository.ListarAutoresDosLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarNomesDiferentesLivros(){
        var resultado = livroRepository.listarNomesDiferentesLivros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosDeLivrosAutoresBrasileiros(){
        var resultado = livroRepository.ListarGenerosAutoresBrasileiros();
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosQueryParamTest(){
        var resultado = livroRepository.findByGenero(GeneroLivro.MISTERIO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void listarGenerosPositionalParamTest(){
        var resultado = livroRepository.findByGenero(GeneroLivro.MISTERIO, "preco");
        resultado.forEach(System.out::println);
    }

    @Test
    void deletarPorGeneroTest(){
       livroRepository.deleteByGenero(GeneroLivro.CIENCIA);
    }

    @Test
    void updateDataPublicacaoTest(){
        livroRepository.updateDataPublicacao(LocalDate.of(2000, 01, 01));
    }
}