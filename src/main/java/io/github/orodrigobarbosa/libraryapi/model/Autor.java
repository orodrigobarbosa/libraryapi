package io.github.orodrigobarbosa.libraryapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "autor", schema = "public")
@ToString(exclude = {"livros"})
@EntityListeners(AuditingEntityListener.class) //controla as annottation @CreatedDate e @LastModifiedDate - precisa utilizar a annt @EnableJpaAuditing para ativar essa funcionalidade
public class Autor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(name = "nacionalidade", length = 50, nullable = false)
    private String nacionalidade;


    @CreatedDate //Anotattion preenche a data automaticamente
    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @LastModifiedDate //Anotattion preenche a data da atualização att
    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "id_usuario")
    private UUID idUsuario;


    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL) //OneToMany por padrão utiliza fetch Lazy
    private List<Livro> livros;


}
