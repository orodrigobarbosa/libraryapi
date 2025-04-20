package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.exceptions.OperacaoNaoPermitidaException;
import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import io.github.orodrigobarbosa.libraryapi.repository.LivroRepository;
import io.github.orodrigobarbosa.libraryapi.validator.AutorValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class AutorService {

    private AutorRepository autorRepository;

    private AutorValidator autorValidator;

    private LivroRepository livroRepository;

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator, LivroRepository livroRepository) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
        this.livroRepository = livroRepository;
    }

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


    public boolean autorPossuiLivro(Autor autor) {
        return livroRepository.existsByAutor(autor);

    }


}
