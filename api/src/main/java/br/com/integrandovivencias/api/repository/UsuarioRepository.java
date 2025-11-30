package br.com.integrandovivencias.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.integrandovivencias.api.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // O Spring cria o SQL automaticamente: SELECT * FROM usuarios WHERE email = ?
    Optional<Usuario> findByEmail(String email);
}