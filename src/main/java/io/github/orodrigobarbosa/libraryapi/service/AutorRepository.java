package io.github.orodrigobarbosa.libraryapi.service;

import io.github.orodrigobarbosa.libraryapi.model.Autor;
import org.springframework.data.repository.Repository;

import java.util.UUID;

interface AutorRepository extends Repository<Autor, UUID> {
}
