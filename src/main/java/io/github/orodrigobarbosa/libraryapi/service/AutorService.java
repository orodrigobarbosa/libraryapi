package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
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

    public AutorService(AutorRepository autorRepository, AutorValidator autorValidator) {
        this.autorRepository = autorRepository;
        this.autorValidator = autorValidator;
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










}
