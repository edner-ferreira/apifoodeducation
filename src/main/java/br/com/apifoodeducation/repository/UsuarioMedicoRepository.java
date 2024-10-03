package br.com.apifoodeducation.repository;

import br.com.apifoodeducation.model.UsuarioMedico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioMedicoRepository extends JpaRepository<UsuarioMedico, Long> {

    UsuarioMedico findByEmail(String email);

    UsuarioMedico findByCrm(String crm);
}
