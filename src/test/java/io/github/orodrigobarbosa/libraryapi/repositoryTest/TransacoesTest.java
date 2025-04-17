package io.github.orodrigobarbosa.libraryapi.repositoryTest;


import io.github.orodrigobarbosa.libraryapi.service.TransacaoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class TransacoesTest {

    @Autowired
    TransacaoService transacaoService;



    @Test
    void transacaoSimples() {
        transacaoService.executar();
    }

    @Test
    void transacoesSemAtualizar() { //estado managed
        transacaoService.atualizacaoSemAtualizar();
    }
}
