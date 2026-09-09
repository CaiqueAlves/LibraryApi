package io.github.caiquealves.libraryapi.controller;

import io.github.caiquealves.libraryapi.controller.dto.CadastroLivroDTO;
import io.github.caiquealves.libraryapi.controller.dto.ErroResposta;
import io.github.caiquealves.libraryapi.controller.dto.ResultadoPesquisaDTO;
import io.github.caiquealves.libraryapi.controller.mappers.LivroMapper;
import io.github.caiquealves.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.caiquealves.libraryapi.model.Livro;
import io.github.caiquealves.libraryapi.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/livros")
//http://localhost:8080/livros
@RequiredArgsConstructor
public class LivroController implements GenericController {

    private final LivroService service;
    private final LivroMapper mapper;

    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody @Valid CadastroLivroDTO dto) {
        Livro livro = mapper.toEntity(dto);
        service.salvar(livro);
        var url = gerarHeaderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<ResultadoPesquisaDTO> obterDetalhes(@PathVariable ("id") String id){
        return  service.obterPorId(UUID.fromString(id)).
                map(Livro -> {
                    var dto = mapper.toDTO(Livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deletar(@PathVariable("id") String id){
        return service.obterPorId(UUID.fromString(id)).
                map(Livro -> {
                    service.deletar(Livro);
                    return ResponseEntity.noContent().build();
                }).orElseGet(()-> ResponseEntity.notFound().build());
    }
}
