package io.github.orodrigobarbosa.libraryapi.controller.dto;


import io.github.orodrigobarbosa.libraryapi.model.Autor;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO(

        UUID id,

        @Size(min = 2, max = 100, message =  "fora do tamanho aceitável")
        @NotBlank(message = "Campo obrigatório")
        String nome,

        @NotNull(message = "Campo obrigatório")
        @Past(message = "não pode ser data futura")
        LocalDate dataNascimento,

        @Size(min = 2, max = 50, message = "fora do tamanho aceitável")
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
