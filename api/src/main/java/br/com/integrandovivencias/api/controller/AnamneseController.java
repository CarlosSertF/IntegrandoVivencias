package br.com.integrandovivencias.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.integrandovivencias.api.dto.AnamneseRequestDTO;
import br.com.integrandovivencias.api.dto.AnamneseResponseDTO;
import br.com.integrandovivencias.api.service.AnamneseService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/anamneses")
public class AnamneseController {

    @Autowired
    private AnamneseService service;

    @PostMapping
    public ResponseEntity<AnamneseResponseDTO> cadastrar(@RequestBody AnamneseRequestDTO dados, UriComponentsBuilder uriBuilder) {
        AnamneseResponseDTO salvo = service.cadastrar(dados);
        URI uri = uriBuilder.path("/anamneses/{id}").buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping
    public List<AnamneseResponseDTO> listar() {
        return service.listarTodas();
    }
}