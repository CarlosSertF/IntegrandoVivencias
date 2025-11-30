package br.com.integrandovivencias.api.dto;

public record LoginRequestDTO(
    String email, 
    String senha
) {}