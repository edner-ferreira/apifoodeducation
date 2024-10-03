package br.com.apifoodeducation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apifoodeducation.model.RefeicaoDiaria;
import br.com.apifoodeducation.repository.RefeicaoDiariaRepository;

@Service
public class RefeicaoDiariaService {
	
   @Autowired
   private RefeicaoDiariaRepository refeicaoDiariaRepository;

   public List<RefeicaoDiaria> getAllRefeicaoDiaria() {
	   return refeicaoDiariaRepository.findAll();
   }
   
   public RefeicaoDiaria getRefeicaoDiariaByDescricaoAlimentos(String descricaoAlimentos) {
	   return refeicaoDiariaRepository.findByDescricaoAlimentos(descricaoAlimentos);
   }

   public RefeicaoDiaria addRefeicaoDiaria(RefeicaoDiaria refeicaoDiaria) {
	   return refeicaoDiariaRepository.save(refeicaoDiaria);
   }
	
   public RefeicaoDiaria getRefeicaoDiariaById(Long id) {
	   // TODO Auto-generated method stub
	   return refeicaoDiariaRepository.getById(id);
   }
	  
}
