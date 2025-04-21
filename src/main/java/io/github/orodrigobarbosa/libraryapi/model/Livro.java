package io.github.orodrigobarbosa.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.validator.constraints.ISBN;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table(name = "livro")
@Entity
public class Livro {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "isbn", length = 20, nullable = false)
    @ISBN(type = ISBN.Type.ANY)
    private String isbn;

    @Column(name = "titulo", length = 150, nullable = false)
    private String titulo;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "genero", length = 30, nullable = false)
    private GeneroLivro genero;

    @Column(name = "preco", precision = 18, scale = 2)
    private BigDecimal preco;

    @ManyToOne  //(cascade = CascadeType.ALL)     //serve para operacao de persistancia
    @JoinColumn(name = "id_autor")
    private Autor autor;




}
