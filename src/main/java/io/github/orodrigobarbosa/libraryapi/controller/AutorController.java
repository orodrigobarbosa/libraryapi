package io.github.orodrigobarbosa.libraryapi.controller;

import io.github.orodrigobarbosa.libraryapi.controller.dto.AutorDTO;
import io.github.orodrigobarbosa.libraryapi.service.AutorService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@AllArgsConstructor
@RestController
@RequestMapping("/autores")
public class AutorController {

    private AutorService autorService;


    @PostMapping
    public ResponseEntity<Void> criarAutor(@RequestBody AutorDTO autor) {
        var autorEntidade = autor.mapearParaAutor();
        autorService.salvarAutor(autorEntidade);

        //hhttp://localhost:8080/autores/id
       URI location =  ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(autorEntidade.getId()).toUri();
        return ResponseEntity.created(location).build();
    }


}
