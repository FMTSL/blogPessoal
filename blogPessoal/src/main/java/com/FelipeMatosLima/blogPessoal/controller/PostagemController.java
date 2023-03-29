package com.FelipeMatosLima.blogPessoal.controller;

import com.FelipeMatosLima.blogPessoal.model.Postagem;
import com.FelipeMatosLima.blogPessoal.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController //Anotação que indica que a clase que controla a API REST
@RequestMapping("/postagens") //Anotação que fornece endereço inicial
@CrossOrigin(origins = "*", allowedHeaders = "*") // Indica quais origens e cabeçalhos de HTTP podem ter acesso a API
public class PostagemController {

    @Autowired //Responsável por fazer a injeção de dependência do Spring, trazendo os métodos criados da repository
    private PostagemRepository postagemRepository;

    @GetMapping
    public ResponseEntity<List<Postagem>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());

    }
}
