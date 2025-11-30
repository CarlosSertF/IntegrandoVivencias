package br.com.integrandovivencias.api.dto;

public record AnamneseRequestDTO(
    String queixaAtual,
    boolean temLaudo,
    String laudoDescricao,
    boolean tomaMedicacao,
    String medicacaoDescricao,
    boolean fazAcompanhamento,
    String acompanhamentoDescricao,
    boolean temDificuldadeFala,
    String preferenciasBrincadeiras,

    // IDs obrigatórios para fazer a ligação
    Long pacienteId,
    Long usuarioId
) {}