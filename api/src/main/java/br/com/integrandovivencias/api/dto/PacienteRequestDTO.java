package br.com.integrandovivencias.api.dto;

import java.time.LocalDate;

public record PacienteRequestDTO(
    String nome,
    LocalDate data_nascimento,
    Long usuarioId
) {}