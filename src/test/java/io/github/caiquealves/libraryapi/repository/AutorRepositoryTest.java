package io.github.caiquealves.libraryapi.repository;

import io.github.caiquealves.libraryapi.Model.Autor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {
    @Autowired
    private AutorRepository repository;

    @Test
    public void salvarTest(){
        Autor autor = new Autor();
        autor.setNome("maria");
        autor.setNacionalidade("Brasileiro");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));
        repository.save(autor);

        var autorSalvo = repository.save(autor);
        System.out.println( "Autor salvo"+autorSalvo);
    }

    @Test
    public void atualizarTest(){
        var id = UUID.fromString("77c1e3f0-823c-4845-9ff5-88ec09617abe");

        Optional<Autor> possivelAutor = repository.findById(id);

        if(possivelAutor.isPresent()){
            Autor autorEncontrado =  possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1967, 1, 30));

            repository.save(autorEncontrado);
        }
    }
}
