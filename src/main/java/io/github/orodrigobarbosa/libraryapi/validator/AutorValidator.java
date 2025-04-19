package io.github.orodrigobarbosa.libraryapi.validator;

import io.github.orodrigobarbosa.libraryapi.exceptions.RegistroDuplicadoException;
import io.github.orodrigobarbosa.libraryapi.model.Autor;
import io.github.orodrigobarbosa.libraryapi.repository.AutorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutorValidator {

    private AutorRepository autorRepository;


    public AutorValidator(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }


    public void validar(Autor autor){
        if (existeAutorCadastrado(autor)) {
            throw new RegistroDuplicadoException("Já existe um autor cadastrado com este nome e data de nascimento");
        }
    }

    
    
    private boolean existeAutorCadastrado(Autor autor){
        Optional<Autor> autorEncontrado = autorRepository.findByNomeAndDataNascimentoAndNacionalidade(
                autor.getNome(),
                autor.getDataNascimento(),
                autor.getNacionalidade());

        if (autor.getId() == null) {
            return autorEncontrado.isPresent();
        }

        return !autor.getId().equals(autorEncontrado.get().getId()) && autorEncontrado.isPresent();
    }

}
