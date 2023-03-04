package com.example.restservice;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.restservice.controller.PessoaController;
import com.example.restservice.models.Pessoa;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaControllerTest {

    @Autowired
    private PessoaController pessoaController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testAddPessoa() {
        Pessoa pessoa = new Pessoa("João", 30);

        ResponseEntity<Pessoa> responseEntity = restTemplate.postForEntity("/pessoas", pessoa, Pessoa.class);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        List<Pessoa> pessoas = Arrays.asList(restTemplate.getForObject("/pessoas", Pessoa[].class));

        assertTrue(pessoas.contains(pessoa));

        int posicao = pessoaController.obterPosicaoNaFila(pessoa.getId());
        assertEquals(pessoas.size(), posicao);
    }
}

