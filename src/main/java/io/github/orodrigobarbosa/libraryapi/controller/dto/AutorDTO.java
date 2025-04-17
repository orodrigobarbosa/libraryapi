package io.github.orodrigobarbosa.libraryapi.controller.dto;


import java.time.LocalDate;

public record AutorDTO(String nome, LocalDate dataNascimento, String nacionalidade) {
    //esse DTO apenas um objeto que representara o Json
}
