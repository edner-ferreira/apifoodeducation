package br.com.apifoodeducation.controller;

import br.com.apifoodeducation.dto.MensagemSucessoDTO;
import br.com.apifoodeducation.dto.UsuarioComumDTO;
import br.com.apifoodeducation.model.UsuarioComum;
import br.com.apifoodeducation.service.UsuarioComumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/usuario-comum")
public class UsuarioComumController {

    @Autowired
    private UsuarioComumService usuarioComumService;

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioComum> salvarUsuarioComum(@RequestBody UsuarioComumDTO usuarioComumDTO){
//        System.out.println("senha - " + usuarioComum.getSenha());
        UsuarioComum usuarioComumSalvo = usuarioComumService.savar(usuarioComumDTO);
        return ResponseEntity.ok(usuarioComumSalvo);
    }

    @GetMapping("/buscarporemail")
    public ResponseEntity<UsuarioComum> bucarPorEmail(@RequestBody UsuarioComumDTO usuarioComumDTO){
        return ResponseEntity.ok(usuarioComumService.buscaPorEmail(usuarioComumDTO));
    }

    @PutMapping("/editarusuario/{id}")
    public ResponseEntity<UsuarioComum> editarUsuario(@PathVariable Long id, @RequestBody UsuarioComumDTO usuarioComumDTO){
        UsuarioComum usuarioComumEditado = usuarioComumService.editarUsuario(id, usuarioComumDTO);
        if (usuarioComumEditado != null) {
            return ResponseEntity.ok(usuarioComumEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletarusuario/{id}")
    public ResponseEntity<MensagemSucessoDTO> deletarUsuario(@PathVariable Long id) {
        MensagemSucessoDTO mensagemSucessoDTO = new MensagemSucessoDTO();
        if (usuarioComumService.deletarUsuario(id)) {
            mensagemSucessoDTO.setMensagem("Usuario deletado com sucesso!");
            return ResponseEntity.ok(mensagemSucessoDTO);
        }
        mensagemSucessoDTO.setMensagem("Usuario inexistente ou não cadastrado!");
        return ResponseEntity.ok(mensagemSucessoDTO);
    }
}
