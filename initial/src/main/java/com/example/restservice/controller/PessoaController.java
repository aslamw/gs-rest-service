package com.example.restservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.restservice.models.OrganizacaoFila;
import com.example.restservice.models.Pessoa;
import com.example.restservice.models.PessoaDAO;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {
    
    @Autowired
    private PessoaDAO pessoaDAO;

    @PostMapping
    public ResponseEntity<Pessoa> adicionarPessoa(@RequestBody Pessoa pessoa) {
        pessoaDAO.adicionar(pessoa);
        enviarSMS(pessoa);
        return new ResponseEntity<>(pessoa, HttpStatus.CREATED);
    }
    
    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoaDAO.listarTodos();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> getPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        if (pessoa != null) {
            return new ResponseEntity<>(pessoa, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        Pessoa pessoaExistente = pessoaDAO.buscarPorId(id);
        if (pessoaExistente != null) {
            pessoaExistente.setNome(pessoa.getNome());
            pessoaExistente.setEmail(pessoa.getEmail());
            pessoaDAO.atualizar(pessoaExistente);
            return new ResponseEntity<>(pessoaExistente, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaDAO.buscarPorId(id);
        if (pessoa != null) {
            pessoaDAO.remover(pessoa);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    private void enviarSMS(Pessoa pessoa) {
        try {
            String customerId = "id";
            String apiKey = "não deu tempo de entrar na API";
            String phoneNumber = pessoa.getTelefone();
            String message = "Bem-vindo(a) à nossa plataforma, " + pessoa.getNome() + "!";
            
            TelesignMessagingClient client = new TelesignMessagingClient(customerId, apiKey);
            MessagingResponse response = client.message(phoneNumber, message);
            if (response.isSuccessful()) {
                System.out.println("SMS enviado para " + phoneNumber);
            } else {
                System.out.println("Falha ao enviar SMS para " + phoneNumber);
            }
        } catch (Exception e) {
            System.out.println("Erro ao enviar SMS: " + e.getMessage());
        }
    }
}

