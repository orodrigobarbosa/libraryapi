package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import io.github.orodrigobarbosa.libraryapi.repository.LivroRepository;
import io.github.orodrigobarbosa.libraryapi.validator.AutorValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class AutorService {

    private final AutorRepository autorRepository;

    private final AutorValidator autorValidator;

    private final LivroRepository livroRepository;


    public Autor salvarAutor(Autor autor) {
        autorValidator.validar(autor);
        return autorRepository.save(autor);

    }

    public Autor atualizarAutor(Autor autor) {
        if (autor.getId() == null) {
            throw new IllegalArgumentException("Para atualizar, se faz necessário que o autor já esteja salvo na base de dados e tenha um id");
        }
        autorValidator.validar(autor);
        return autorRepository.save(autor);
    }


    public Optional<Autor> buscarPorId(UUID id) {
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor) {
        if (autorPossuiLivro(autor)) {
            throw new OperacaoNaoPermitidaException("Não é permitido deletar. Autor(a) possui livros cadastrados!");
        }

        autorRepository.delete(autor);
    }


    //este metodo faz 3 coisas.. o ideal é cada metodo fazer uma unica coisa. Será melhorado
    public List<Autor> listarAutoresPorNomeENacionalidade(String nome, String nacionalidade) {


        if (nome != null && nacionalidade != null) {
            return autorRepository.findByNomeAndNacionalidade(nome, nacionalidade);
        }

        if (nome != null) {
            return autorRepository.findByNome(nome);
        }

        if (nacionalidade != null) {
            return autorRepository.findByNacionalidade(nacionalidade);
        }
        return autorRepository.findAll();
    }

    public List<Autor> pesquisaByExample
            (String nome, String nacionalidade) {
        var autor = new Autor();
        autor.setNome(nome);
        autor.setNacionalidade(nacionalidade);

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreNullValues()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example<Autor> autorExample = Example.of(autor, matcher);
        return autorRepository.findAll(autorExample);
    }

    public boolean autorPossuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);

    }


}
