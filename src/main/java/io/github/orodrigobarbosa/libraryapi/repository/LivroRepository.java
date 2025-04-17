package io.github.orodrigobarbosa.libraryapi.repository;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.model.GeneroLivro;
import io.github.orodrigobarbosa.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


/**
 * @see LivroRepositoryTest
 */

@Repository
public interface LivroRepository extends JpaRepository<Livro, UUID> {

//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html


    List<Livro> findByAutor(Autor autor);

    //select * from livro where titulo = titulo
    List<Livro> findByTitulo(String titulo);

    List<Livro> findByIsbn(String isbn);

    //select * from livro where titulo = ?  and preco = ?
    List<Livro> findByTituloAndPreco(String titulo, BigDecimal preco);

    List<Livro> findByTituloAndPrecoOrderByTitulo(String titulo, BigDecimal preco);

    //select * from livro where titulo = ?  or isbn = ?
    List<Livro> findByTituloOrIsbn(String titulo, String isbn);

    @Query(" select l from Livro as l order by l.titulo ")
    List<Livro> listarTodosOrdenadoPorTitulo();

    @Query(" select l from Livro as l order by l.titulo, l.preco ")
    List<Livro> listarTodosOrdenadoPorTituloAndPreco();

    /**
     * select a.*
     * from livro l
     * join autor a on a.id = l.id_autor
     */
    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

    //delect distinct l.* from livro l
    @Query("select distinct l.titulo from Livro l")
    List<String> listarLivrosNomesDiferentes();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            """)
    List<String> listarGenerosAutoresBrasileiros();

    //NAMED PARAMETERS
    @Query("select l from Livro l where l.genero= :genero order by :parametro")
    List<Livro> findByGenero(@Param("genero") GeneroLivro generoLivro, String parametro);

    //POSITIONAL PARAMETERS
    @Query("select l from Livro l where l.genero= ?1 order by ?2 ")
    List<Livro> findByGeneroPositionalParameters(GeneroLivro generoLivro, String parametro);



    @Modifying
    @Transactional
    @Query("delete from Livro where genero = ?1")
    void deleteByGenero(GeneroLivro genero);


    @Modifying
    @Transactional
    @Query("update Livro set dataPublicacao = ?1")
    void updateLivroDataPublicacao(LocalDate novaData);
}
