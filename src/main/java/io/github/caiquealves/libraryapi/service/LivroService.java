package io.github.caiquealves.libraryapi.service;

import io.github.caiquealves.libraryapi.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroService {
    private final LivroRepository livroRepository;
}
