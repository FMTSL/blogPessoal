package com.FelipeMatosLima.blogPessoal.controller;

import com.FelipeMatosLima.blogPessoal.model.Postagem;
import com.FelipeMatosLima.blogPessoal.repository.PostagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController //Anotação que indica que a clase que controla a API REST
@RequestMapping("/postagens") //Anotação que fornece endereço inicial
@CrossOrigin(origins = "*", allowedHeaders = "*") // Indica quais origens e cabeçalhos de HTTP podem ter acesso a API
public class PostagemController {

    @Autowired //Responsável por fazer a injeção de dependência do Spring, trazendo os métodos criados da repository
    private PostagemRepository postagemRepository;

    // Criação do método GET
    @GetMapping
    public ResponseEntity<List<Postagem>> getAll(){
        return ResponseEntity.ok(postagemRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Postagem> getById(@PathVariable Long id){
        return postagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public  ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    // Criação do método POST
    @PostMapping
    public ResponseEntity<Postagem> post (@Valid @RequestBody Postagem postagem) {
        return  ResponseEntity.status(HttpStatus.CREATED)
                .body(postagemRepository.save(postagem));
    }
    // Criação do método PUT
    @PutMapping
    public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
        return postagemRepository.findById(postagem.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(postagemRepository.save(postagem)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        Optional<Postagem> postagem = postagemRepository.findById(id);

        if(postagem.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        postagemRepository.deleteById(id);
    }
}
