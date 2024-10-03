package br.com.apifoodeducation.controller;

import java.util.List;

import br.com.apifoodeducation.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apifoodeducation.dto.UsuarioRefeicoesDTO;
import br.com.apifoodeducation.service.DicasAlimentaresService;

@RestController
@RequestMapping("/api/dicas-alimentares")
//@CrossOrigin(origins = "http://localhost:4200")
public class DicasAlimentaresController {
	
	@Autowired
    private DicasAlimentaresService dicasAlimentaresService;

    @PostMapping("/sugerir")
    public List<DicaAlimentar> sugerirDicasAlimentares(@RequestBody UsuarioRefeicoesDTO usuarioRefeicoesDTO) {
        UsuarioComum usuarioComum = (UsuarioComum) usuarioRefeicoesDTO.getUsuario();
        List<ConsumoAlimento> consumoAlimentos = usuarioRefeicoesDTO.getConsumoAlimentos();
        return dicasAlimentaresService.sugerirDicasAlimentares(usuarioComum, consumoAlimentos);
    }
}
