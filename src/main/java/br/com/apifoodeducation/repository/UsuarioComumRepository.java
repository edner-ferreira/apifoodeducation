package br.com.apifoodeducation.repository;

import br.com.apifoodeducation.model.UsuarioComum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioComumRepository extends JpaRepository<UsuarioComum, Long> {

    UsuarioComum findByEmail(String email);
}
