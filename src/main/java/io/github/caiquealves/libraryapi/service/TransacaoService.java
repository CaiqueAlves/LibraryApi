package io.github.caiquealves.libraryapi.service;

import io.github.caiquealves.libraryapi.model.Autor;
import io.github.caiquealves.libraryapi.model.GeneroLivro;
import io.github.caiquealves.libraryapi.model.Livro;
import io.github.caiquealves.libraryapi.repository.AutorRepository;
import io.github.caiquealves.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository  autorRepository;
    @Autowired
    private LivroRepository livroRepository;


    @Transactional
    public void atualizarSemAtualizar(){
        var livro = livroRepository.findById(UUID.fromString("02943472-349f-4f97-8f44-d8306b8eb82f")).orElse(null);
        livro.setDataPublicacao(LocalDate.now());
    }
    @Transactional
    public void executar(){

        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90887-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback!");
        }
    }
}
