package io.github.orodrigobarbosa.libraryapi.controller.dto;


import io.github.orodrigobarbosa.libraryapi.model.Autor;

import java.time.LocalDate;

public record AutorDTO(String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {
    //esse DTO é apenas um objeto que representara o Json



    public Autor mapearParaAutor(){
        Autor autor = new Autor();
        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);
        return autor;
    }
}
