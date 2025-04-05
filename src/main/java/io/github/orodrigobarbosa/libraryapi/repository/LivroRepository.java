package io.github.orodrigobarbosa.libraryapi.repository;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;


/**
 * @see  LivroRepositoryTest
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
     *
     */
    @Query(" select a from Livro l join l.autor a ")
    List<Autor> listarAutoresDosLivros();

//delect distinct l.* from livro l
    @Query ("select distinct l.titulo from Livro l")
    List<String> listarLivrosNomesDiferentes();

    @Query("""
            select l.genero
            from Livro l
            join l.autor a
            where a.nacionalidade = 'Brasileira'
            """)
    List<String> listarGenerosAutoresBrasileiros();


}
