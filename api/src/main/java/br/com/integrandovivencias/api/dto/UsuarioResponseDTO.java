package br.com.integrandovivencias.api.dto;

import br.com.integrandovivencias.api.model.Usuario;

public record UsuarioResponseDTO(
    Long id,
    String nome,
    String email,
    String cargo
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getCargo());
    }
}