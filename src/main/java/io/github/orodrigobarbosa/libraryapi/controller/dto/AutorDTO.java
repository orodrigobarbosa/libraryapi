package io.github.orodrigobarbosa.libraryapi.controller.dto;


import io.github.orodrigobarbosa.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(

        UUID id,

        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotNull(message = "Campo obrigatório")
        LocalDate dataNascimento,

        @NotBlank(message = "Campo obrigatório")
        String nacionalidade) {
    //esse DTO é apenas um objeto que representara o Json


    public Autor mapearParaAutor() {
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
