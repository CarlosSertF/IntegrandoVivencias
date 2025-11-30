package br.com.integrandovivencias.api.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.integrandovivencias.api.dto.PacienteRequestDTO;
import br.com.integrandovivencias.api.dto.PacienteResponseDTO;
import br.com.integrandovivencias.api.model.Paciente;
import br.com.integrandovivencias.api.model.Usuario; // <--- Import novo
import br.com.integrandovivencias.api.repository.PacienteRepository;
import br.com.integrandovivencias.api.repository.UsuarioRepository; // <--- Import novo

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository; // <--- 1. Precisamos dele para buscar o profissional

    public PacienteResponseDTO cadastrar(PacienteRequestDTO dados) {
        
        // 2. Buscamos o usuário no banco pelo ID que veio do Frontend
        // Se o ID não vier ou não existir, soltamos um erro.
        if (dados.usuarioId() == null) {
             throw new RuntimeException("O ID do profissional responsável é obrigatório!");
        }

        Usuario profissional = usuarioRepository.findById(dados.usuarioId())
                .orElseThrow(() -> new RuntimeException("Profissional não encontrado com ID: " + dados.usuarioId()));

        // 3. Agora criamos o paciente passando o 'profissional' como 4º argumento
        Paciente novoPaciente = new Paciente(
            LocalDate.now(),          // data_cadastro
            dados.data_nascimento(),  // data_nascimento
            dados.nome(),             // nome
            profissional              // <--- O profissional responsável (Correção do erro!)
        );

        repository.save(novoPaciente);
        return new PacienteResponseDTO(novoPaciente);
    }

    public List<PacienteResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(PacienteResponseDTO::new)
                .toList();
    }

    public void excluir(Long id) {
        // Verifica se existe antes de tentar apagar
        if (!repository.existsById(id)) {
            throw new RuntimeException("Paciente não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}

