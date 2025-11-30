package br.com.integrandovivencias.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.integrandovivencias.api.dto.LoginRequestDTO;
import br.com.integrandovivencias.api.dto.UsuarioRequestDTO;
import br.com.integrandovivencias.api.dto.UsuarioResponseDTO;
import br.com.integrandovivencias.api.service.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth") // Prefixo para autenticação
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    // POST /auth/register -> Cria o Admin
    @PostMapping("/register")
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO dados, UriComponentsBuilder uriBuilder) {
        UsuarioResponseDTO usuarioSalvo = service.cadastrar(dados);
        URI uri = uriBuilder.path("/usuario/{id}").buildAndExpand(usuarioSalvo.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioSalvo);
    }

    // POST /auth/login -> Faz o Login (RF01)
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> login(@RequestBody LoginRequestDTO dados) {
        try {
            UsuarioResponseDTO usuarioLogado = service.logar(dados);
            return ResponseEntity.ok(usuarioLogado); // Retorna 200 OK se der certo
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).build(); // Retorna 401 Unauthorized se a senha estiver errada
        }
    }


    @GetMapping 
    public List<UsuarioResponseDTO> listar() {
        return service.listarTodos();
    }

}