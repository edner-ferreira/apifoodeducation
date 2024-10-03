package br.com.apifoodeducation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.apifoodeducation.model.RefeicaoDiaria;

@Repository
public interface RefeicaoDiariaRepository extends JpaRepository<RefeicaoDiaria, Long> {

    @Query("SELECT a FROM RefeicaoDiaria a WHERE a.descricaoAlimentos = :descricaoAlimentos")
    public RefeicaoDiaria findByDescricaoAlimentos(@Param("descricaoAlimentos") String descricaoAlimentos);
}
