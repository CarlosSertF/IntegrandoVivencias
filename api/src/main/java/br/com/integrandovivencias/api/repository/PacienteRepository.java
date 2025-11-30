package br.com.integrandovivencias.api.repository;

import br.com.integrandovivencias.api.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Repository padrão, pronto para usar!
}