package br.com.integrandovivencias.api.dto;

import java.time.LocalDate;

import br.com.integrandovivencias.api.model.Paciente;

public record PacienteResponseDTO(
    Long id,
    String nome,
    LocalDate data_nascimento,
    LocalDate data_cadastro,
    String nomeProfissional
) {
    public PacienteResponseDTO(Paciente paciente) {
        this(
            paciente.getId(),
            paciente.getNome(),
            paciente.getData_nascimento(), // Usando seu nome com underline
            paciente.getData_cadastro(),
            paciente.getUsuario() != null ? paciente.getUsuario().getNome() : "Desconhecido"
        );
    }
}