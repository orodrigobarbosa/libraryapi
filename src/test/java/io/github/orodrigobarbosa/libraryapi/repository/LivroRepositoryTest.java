package io.github.orodrigobarbosa.libraryapi.repository;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.model.GeneroLivro;
import io.github.orodrigobarbosa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
    void salvarLivroTest() {
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
}