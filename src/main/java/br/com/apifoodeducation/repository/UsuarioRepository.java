package br.com.apifoodeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifoodeducation.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    Usuario findByEmail(String email);
}
