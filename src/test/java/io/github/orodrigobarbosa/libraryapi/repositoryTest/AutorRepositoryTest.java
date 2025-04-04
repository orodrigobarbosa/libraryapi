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
        autor.setNome("Belchior");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1954, 1, 28));

        var autorSalvo = autorRepository.save(autor);
        System.out.println("Autor salvo: " + autorSalvo);
    }

    @Test
    public void atualizarTest() {
        var id = UUID.fromString("9f7db8c0-b166-4802-8f1a-8f2f79177b0c");

        Optional<Autor> possivelAutor = autorRepository.findById(id);
        if (possivelAutor.isPresent()) {
            Autor autorEncontrado = possivelAutor.get();
            System.out.println("Dados do Autor: ");
            System.out.println(autorEncontrado);

            autorEncontrado.setNome("Dogshow");
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
        var id = UUID.fromString("a8ea4e3e-3ce9-42ce-ac25-bab874fff4c4");
        autorRepository.deleteById(id);
    }
}
