package br.com.integrandovivencias.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.integrandovivencias.api.dto.AnamneseRequestDTO;
import br.com.integrandovivencias.api.dto.AnamneseResponseDTO;
import br.com.integrandovivencias.api.model.Anamnese;
import br.com.integrandovivencias.api.model.Paciente;
import br.com.integrandovivencias.api.model.Usuario;
import br.com.integrandovivencias.api.repository.AnamneseRepository;
import br.com.integrandovivencias.api.repository.PacienteRepository;
import br.com.integrandovivencias.api.repository.UsuarioRepository;

@Service
public class AnamneseService {

    @Autowired
    private AnamneseRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public AnamneseResponseDTO cadastrar(AnamneseRequestDTO dados) {
        // 1. Busca o Paciente (Se não achar, erro)
        Paciente paciente = pacienteRepository.findById(dados.pacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado!"));

        // 2. Busca o Usuário (Se não achar, erro)
        Usuario usuario = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // 3. Cria a Anamnese usando o seu construtor cheio
        Anamnese novaAnamnese = new Anamnese(
            dados.queixaAtual(),
            dados.temLaudo(),
            dados.laudoDescricao(),
            dados.tomaMedicacao(),
            dados.medicacaoDescricao(),
            dados.fazAcompanhamento(),
            dados.acompanhamentoDescricao(),
            dados.temDificuldadeFala(),
            dados.preferenciasBrincadeiras(),
            paciente, // Passamos o objeto paciente inteiro
            usuario   // Passamos o objeto usuario inteiro
        );

        // A data é gerada automaticamente no atributo da classe (LocalDateTime.now())

        repository.save(novaAnamnese);

        return new AnamneseResponseDTO(novaAnamnese);
    }

    public List<AnamneseResponseDTO> listarTodas() {
        return repository.findAll().stream()
                .map(AnamneseResponseDTO::new)
                .toList();
    }
}