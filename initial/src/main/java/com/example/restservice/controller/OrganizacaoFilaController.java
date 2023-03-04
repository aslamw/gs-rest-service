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

@RestController
@RequestMapping("/organizacaoFila")
public class OrganizacaoFilaController {
 
   @Autowired
   private OrganizacaoFilaDAO organizacaoFilaDAO;
 
   @GetMapping
   public List<OrganizacaoFila> list() {
       return organizacaoFilaDAO.list();
   }
 
   @PostMapping
   public void save(@RequestBody OrganizacaoFila organizacaoFila) {
       organizacaoFilaDAO.save(organizacaoFila);
       //enviar SMS
       enviarSMS(organizacaoFila.getPessoa());
   }
 
   @GetMapping("/{id}")
   public OrganizacaoFila getById(@PathVariable("id") long id) {
       return organizacaoFilaDAO.getById(id);
   }
 
   @PutMapping("/{id}")
   public void update(@PathVariable("id") long id, @RequestBody OrganizacaoFila organizacaoFila) {
       OrganizacaoFila organizacaoFilaExistente = organizacaoFilaDAO.getById(id);
       if (organizacaoFilaExistente != null) {
           organizacaoFila.setId(id);
           organizacaoFilaDAO.update(organizacaoFila);
       }
   }
 
   @DeleteMapping("/{id}")
   public void delete(@PathVariable("id") long id) {
       OrganizacaoFila organizacaoFila = organizacaoFilaDAO.getById(id);
       if (organizacaoFila != null) {
           organizacaoFilaDAO.delete(organizacaoFila);
       }
   }
   
   //Método para enviar o SMS utilizando a API do TeleSign
   private void enviarSMS(Pessoa pessoa) {
       //não deu tempo
   }
}
