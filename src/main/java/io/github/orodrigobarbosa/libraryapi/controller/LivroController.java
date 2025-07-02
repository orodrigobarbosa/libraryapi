package io.github.orodrigobarbosa.libraryapi.controller;


import io.github.orodrigobarbosa.libraryapi.repository.LivroRepository;
import io.github.orodrigobarbosa.libraryapi.service.LivroService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;
}
