package io.github.orodrigobarbosa.libraryapi.controller;

import io.github.orodrigobarbosa.libraryapi.controller.dto.AutorDTO;
import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autores")
public class AutorController {





    @PostMapping
    public ResponseEntity<Autor> criarAutor(@RequestBody AutorDTO autor){

        return new ResponseEntity("Autor criado com sucesso! " + autor, HttpStatus.CREATED);
    }


}
