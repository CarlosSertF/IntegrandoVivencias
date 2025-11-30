package br.com.integrandovivencias.api.dto;

import java.time.LocalDateTime;

import br.com.integrandovivencias.api.model.Anamnese;

public record AnamneseResponseDTO(
    Long id,
    LocalDateTime dataRealizacao,
    String queixaAtual,
    String nomePaciente, // Devolvemos o nome para facilitar
    String nomeUsuario   // Devolvemos o nome de quem atendeu
) {
    public AnamneseResponseDTO(Anamnese anamnese) {
        this(
            anamnese.getId(),
            anamnese.getDataRealizacao(),
            anamnese.getQueixaAtual(),
            anamnese.getPaciente().getNome(),
            anamnese.getUsuario().getNome()
        );
    }
}