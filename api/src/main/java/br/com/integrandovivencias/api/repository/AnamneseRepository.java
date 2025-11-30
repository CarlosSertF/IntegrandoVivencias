package br.com.integrandovivencias.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.integrandovivencias.api.model.Anamnese;

@Repository
public interface AnamneseRepository extends JpaRepository<Anamnese, Long> {
}