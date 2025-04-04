package io.github.orodrigobarbosa.libraryapi.repositoryTest;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.model.GeneroLivro;
import io.github.orodrigobarbosa.libraryapi.model.Livro;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import io.github.orodrigobarbosa.libraryapi.repository.LivroRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
class LivroRepositoryTest {

    @Autowired
    LivroRepository livroRepository;
    @Autowired
    AutorRepository autorRepository;

    @Test
    void salvarLivroTest() { //Este metodo primeiro busca o autor via ID, setta ao livro, e só depois salva o livro
        Livro livro = new Livro();
        livro.setIsbn("9990-3330");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Livro Test");
        livro.setDataPublicacao(LocalDate.of(1990, 2, 28));

        Autor autor = autorRepository
                .findById(UUID.fromString("a549acb9-efd2-4ff2-a3a6-31a6166a3207"))
                .orElse(null);

        livro.setAutor(autor);

        livroRepository.save(livro);

    }


    //serve para operacao de persistancia
    @Test
    void salvarCascadeTest() {
        Livro livro = new Livro();
        livro.setIsbn("7770-3330");
        livro.setPreco(BigDecimal.valueOf(80));
        livro.setGenero(GeneroLivro.ROMANCE);
        livro.setTitulo("Livro Romance - Teste Cascade");
        livro.setDataPublicacao(LocalDate.of(1980, 4, 15));

        Autor autor = new Autor();
        autor.setNome("Renato");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1960, 6, 18));

        livro.setAutor(autor);

        livroRepository.save(livro);
    }

    @Test
        // faz a mesma coisa que cascade, porem manual
    void salvarAutorELivroTest() {

        Autor autor = new Autor();
        autor.setNome("RodrigoBarbosa");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1960, 6, 18));


        Livro livro = new Livro();
        livro.setIsbn("7770-0000");
        livro.setPreco(BigDecimal.valueOf(30));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Livro Romance - Teste Autor E Livro");
        livro.setDataPublicacao(LocalDate.of(1980, 4, 15));

        //salva o autor
        autorRepository.save(autor);
        //setta o autor ao livro que será salvo
        livro.setAutor(autor);
        //salva o livro
        livroRepository.save(livro);
    }


    @Test
    void atualizarAutorDoLivro(){
        UUID id = UUID.fromString("337529dd-481a-4a4f-9f74-8749fc407440"); //id do livre de Maria
        var livroParaAtualizar = livroRepository.findById(id).orElse(null);

        UUID idAutor = UUID.fromString("5fbfca8b-0059-46a6-9ccd-df9c3e934077"); //atualizar o livro para o autor Rodrigo - id do autor
        Autor rodrigo = autorRepository.findById(idAutor).orElse(null);

        livroParaAtualizar.setAutor(rodrigo);

        livroRepository.save(livroParaAtualizar);
    }

    @Test
    void deletarLivro(){
        UUID id = UUID.fromString("337529dd-481a-4a4f-9f74-8749fc407440");
        livroRepository.deleteById(id);
    }

    @Test
    void deletarCascade(){ //o codigo é o mesmo, porem, é deletado o livro e o autor
        UUID id = UUID.fromString("a549acb9-efd2-4ff2-a3a6-31a6166a3207");
        livroRepository.deleteById(id);
    }

    @Test
    @Transactional
    void buscarLivroTeste(){
        UUID id = UUID.fromString("ac4d52a2-fb98-4ea3-bdf1-98957e9c0741");
        Livro livro = livroRepository.findById(id).orElse(null);
        System.out.println("livro: ");
        System.out.println(livro.getTitulo());

        System.out.println("Autor: ");
        System.out.println(livro.getAutor().getNome());
    }

}