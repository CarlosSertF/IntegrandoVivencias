package br.com.integrandovivencias.api.dto;

public record UsuarioRequestDTO(
    String nome,
    String email,
    String senha,
    String cargo
) {}