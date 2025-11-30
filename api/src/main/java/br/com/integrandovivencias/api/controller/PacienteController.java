package br.com.integrandovivencias.api.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.integrandovivencias.api.dto.PacienteRequestDTO;
import br.com.integrandovivencias.api.dto.PacienteResponseDTO;
import br.com.integrandovivencias.api.service.PacienteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService service;

    @PostMapping
    public ResponseEntity<PacienteResponseDTO> cadastrar(@RequestBody PacienteRequestDTO dados, UriComponentsBuilder uriBuilder) {
        PacienteResponseDTO salvo = service.cadastrar(dados);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(salvo.id()).toUri();
        return ResponseEntity.created(uri).body(salvo);
    }

    @GetMapping
    public List<PacienteResponseDTO> listar() {
        return service.listarTodos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
