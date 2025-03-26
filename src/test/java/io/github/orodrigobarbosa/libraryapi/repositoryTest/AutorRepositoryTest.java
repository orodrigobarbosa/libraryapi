package io.github.orodrigobarbosa.libraryapi.repositoryTest;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Test
    public void salvarTest() {
        Autor autor = new Autor();
        autor.setNome("Ana");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1990, 1, 28));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("92f05afb-3a32-4a5f-a352-b74b81fc92e7");

        Optional<Autor> possivelAutor = autorRepository.findById(id);
        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Maria");
            autorRepository.save(autorEncontrado);
        }
    }

    @Test
    public void listarTest() {
        List<Autor> autores = autorRepository.findAll();
        autores.forEach(System.out::println);
    }

    @Test
    public void contarTest() {
        System.out.println("Contage de autores: " + autorRepository.count());
    }

    @Test
    public void deletarPorIdTest() {
        var id = UUID.fromString("c30218a5-ba0a-4c28-8330-5e1c61ec3a3c");
        autorRepository.deleteById(id);
    }
}
