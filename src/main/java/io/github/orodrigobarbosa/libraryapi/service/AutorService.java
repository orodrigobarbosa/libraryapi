package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public Autor salvarAutor(Autor autor){
        return autorRepository.save(autor);

    }
}
