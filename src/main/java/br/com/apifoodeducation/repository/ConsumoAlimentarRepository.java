package br.com.apifoodeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.apifoodeducation.model.ConsumoAlimento;

public interface ConsumoAlimentarRepository extends JpaRepository<ConsumoAlimento, Long>{

}
