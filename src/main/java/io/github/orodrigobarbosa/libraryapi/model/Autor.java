package io.github.orodrigobarbosa.libraryapi.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "autor")
public class Autor {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column (name = "id")
    private UUID id;

    @Column (name = "nome",length = 100, nullable = false )
    private String nome;

    @Column (name = "data_nascimento", nullable = false )
    private LocalDate dataNascimento;

    @Column (name = "nacionalidade", length = 50, nullable = false )
    private LocalDate nacionalidade;

    @Deprecated
    public Autor (){}

}
