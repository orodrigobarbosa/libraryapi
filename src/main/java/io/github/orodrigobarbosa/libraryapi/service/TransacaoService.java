package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.model.GeneroLivro;
import io.github.orodrigobarbosa.libraryapi.model.Livro;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import io.github.orodrigobarbosa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Transactional
    public void atualizacaoSemAtualizar() {
        var livro = livroRepository.findById(UUID.fromString("dd38ecb5-d37b-4ea1-91dd-0f1bcf48aa88")).orElse(null);
        livro.setTitulo("Código Limpo");

        //  livroRepository.save(livro); nao se faz necessario, se está com @Transactional
    }

    @Transactional
    public void executar() {
        //salva autor
        Autor autor = new Autor();
        autor.setNome("José");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1970, 1, 20));

        autorRepository.save(autor);

        //salva livro
        Livro livro = new Livro();
        livro.setIsbn("7340-0000");
        livro.setPreco(BigDecimal.valueOf(40));
        livro.setGenero(GeneroLivro.BIOGRAFIA);
        livro.setTitulo("Livro do José");
        livro.setDataPublicacao(LocalDate.of(2001, 4, 15));

        //setta o autor ao livro que será salvo
        livro.setAutor(autor);

        livroRepository.save(livro);


        if (autor.getNome().equals("José")) {
            throw new RuntimeException("Roll-back!");
        }
    }


}


