package br.com.apifoodeducation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apifoodeducation.model.RefeicaoDiaria;
import br.com.apifoodeducation.service.RefeicaoDiariaService;

@RestController
@RequestMapping("/api/alimentos")
public class RefeicaoDiariaController {
	
	@Autowired
    private RefeicaoDiariaService refeicaoDiariaService;

    @GetMapping
    public List<RefeicaoDiaria> getAllAlimentos() {
        return refeicaoDiariaService.getAllRefeicaoDiaria();
    }

    @PostMapping
    public RefeicaoDiaria addAlimento(@RequestBody RefeicaoDiaria refeicaoDiaria) {
        return refeicaoDiariaService.addRefeicaoDiaria(refeicaoDiaria);
    }

    // Outros métodos para atualizar, excluir, etc.
}
