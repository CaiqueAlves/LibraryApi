package io.github.caiquealves.libraryapi.controller;

import io.github.caiquealves.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.caiquealves.libraryapi.controller.dto.ErroResposta;
import io.github.caiquealves.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.caiquealves.libraryapi.model.Livro;
import io.github.caiquealves.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
//http://localhost:8080/livros
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @PostMapping
    public ResponseEntity<Object>salvarLivro(@RequestBody @Valid CadastroLivroDTO  dto){
        try{
            return ResponseEntity.ok(dto);
        }catch (RegistroDuplicadoException e){
            var erroDTO = ErroResposta.conflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }
}
