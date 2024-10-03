package br.com.apifoodeducation.controller;

import br.com.apifoodeducation.dto.MensagemSucessoDTO;
import br.com.apifoodeducation.dto.UsuarioMedicoDTO;
import br.com.apifoodeducation.model.UsuarioMedico;
import br.com.apifoodeducation.service.UsuarioMedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuario-medico")
public class UsuarioMedicoController {

    @Autowired
    private UsuarioMedicoService usuarioMedicoService;

    @PostMapping("/salvar")
    public ResponseEntity<UsuarioMedico> salvarUsuarioMedico(@RequestBody UsuarioMedicoDTO usuarioMedicoDTO){
//        System.out.println("senha - " + usuarioComum.getSenha());
        UsuarioMedico usuarioMedicoSalvo = usuarioMedicoService.savar(usuarioMedicoDTO);
        return ResponseEntity.ok(usuarioMedicoSalvo);
    }

    @GetMapping("/buscarporemail")
    public ResponseEntity<UsuarioMedico> bucarPorEmail(@RequestBody UsuarioMedicoDTO usuarioMedicoDTO){
        return ResponseEntity.ok(usuarioMedicoService.buscaPorEmail(usuarioMedicoDTO));
    }

    @PutMapping("/editarusuario/{id}")
    public ResponseEntity<UsuarioMedico> editarUsuario(@PathVariable Long id, @RequestBody UsuarioMedicoDTO usuarioMedicoDTO){
        UsuarioMedico usuarioMedicoEditado = usuarioMedicoService.editarUsuario(id, usuarioMedicoDTO);
        if (usuarioMedicoEditado != null) {
            return ResponseEntity.ok(usuarioMedicoEditado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/deletarusuario/{id}")
    public ResponseEntity<MensagemSucessoDTO> deletarUsuario(@PathVariable Long id) {
        MensagemSucessoDTO mensagemSucessoDTO = new MensagemSucessoDTO();
        if (usuarioMedicoService.deletarUsuario(id)) {
            mensagemSucessoDTO.setMensagem("Usuario deletado com sucesso!");
            return ResponseEntity.ok(mensagemSucessoDTO);
        }
        mensagemSucessoDTO.setMensagem("Usuario inexistente ou não cadastrado!");
        return ResponseEntity.ok(mensagemSucessoDTO);
    }
}
