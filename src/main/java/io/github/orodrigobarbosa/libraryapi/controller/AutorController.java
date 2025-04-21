package io.github.orodrigobarbosa.libraryapi.controller;

import io.github.orodrigobarbosa.libraryapi.controller.dto.AutorDTO;
import io.github.orodrigobarbosa.libraryapi.controller.dto.ErroResposta;
import io.github.orodrigobarbosa.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.orodrigobarbosa.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.service.AutorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/autores")
public class AutorController {

    private final AutorService autorService;


    @PostMapping
    public ResponseEntity<Object> criarAutor(@RequestBody @Valid AutorDTO autor) {

        try {
            Autor autorEntidade = autor.mapearParaAutor();
            autorService.salvarAutor(autorEntidade);

            //hhttp://localhost:8080/autores/id
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(autorEntidade.getId())
                    .toUri();

            return ResponseEntity.created(location).build();

        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.erroConflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }

    }


    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable("id") String id) {
        var idAutor = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.buscarPorId(idAutor);
        if (autorOptional.isPresent()) {
            Autor autor = autorOptional.get();
            AutorDTO dto = new AutorDTO(autor.getId(), autor.getNome(), autor.getDataNascimento(), autor.getNacionalidade());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarAutorPorId(@PathVariable("id") String id) {

        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.buscarPorId(idAutor);


            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            autorService.deletar(autorOptional.get());
            return ResponseEntity.noContent().build();
        } catch (OperacaoNaoPermitidaException e) {
            var erroResporta = ErroResposta.erroResposta(e.getMessage());
            return ResponseEntity.status(erroResporta.status()).body(erroResporta);
        }
    }

    @GetMapping
    //AutorDTO porque sempre na entrada e saida estamos usando dto, porque faz parte da camada representativa
    //pesquisar por nome ou nacionaldide
    public ResponseEntity<List<AutorDTO>> listarAutores(@RequestParam(value = "nome", required = false) String
                                                                nome, //value + required false elimina a obrigatoriedade do parametro
                                                        @RequestParam(value = "nacionalidade", required = false) String nacionalidade) {
        List<Autor> resultado = autorService.listarAutoresPorNomeENacionalidade(nome, nacionalidade);
        List<AutorDTO> lista = resultado
                .stream()
                .map(autor -> new AutorDTO(
                        autor.getId(),
                        autor.getNome(),
                        autor.getDataNascimento(),
                        autor.getNacionalidade()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizarAutor(@PathVariable("id") String id, @RequestBody AutorDTO dtoAutor) {

        try {
            var idAutor = UUID.fromString(id);
            Optional<Autor> autorOptional = autorService.buscarPorId(idAutor);

            if (autorOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            var autor = autorOptional.get();
            autor.setNome(dtoAutor.nome());
            autor.setNacionalidade(dtoAutor.nacionalidade());
            autor.setDataNascimento(dtoAutor.dataNascimento());
            autorService.atualizarAutor(autor);

            return ResponseEntity.noContent().build();
        } catch (RegistroDuplicadoException e) {
            var erroDTO = ErroResposta.erroConflito(e.getMessage());
            return ResponseEntity.status(erroDTO.status()).body(erroDTO);
        }
    }


}