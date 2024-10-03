package br.com.apifoodeducation.service;

import br.com.apifoodeducation.dto.UsuarioComumDTO;
import br.com.apifoodeducation.model.*;
import br.com.apifoodeducation.repository.UsuarioComumRepository;
import br.com.apifoodeducation.repository.UsuarioMedicoRepository;
import br.com.apifoodeducation.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioComumService {

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

    @Autowired
    private UsuarioMedicoRepository usuarioMedicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UsuarioComum savar(UsuarioComumDTO usuarioComumDTO){
        if (usuarioComumRepository.findByEmail(usuarioComumDTO.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já esta em uso");
        }

        UsuarioComum usuarioComum = new UsuarioComum();
        usuarioComum.setNomes(usuarioComumDTO.getNomes());
        usuarioComum.setEmail(usuarioComumDTO.getEmail());
//        usuarioComum.setSenha(passwordEncoder.encode(usuarioComumDTO.getSenha()));
        usuarioComum.setSenha(usuarioComumDTO.getSenha());
        usuarioComum.setIdade(usuarioComumDTO.getIdade());
        usuarioComum.setSexo(usuarioComumDTO.getSexo());
        usuarioComum.setAltura(usuarioComumDTO.getAltura());
        usuarioComum.setPeso(usuarioComumDTO.getPeso());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_USER);
        usuarioComum.setRoles(roles);

        if(usuarioComumDTO.getEndereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(usuarioComumDTO.getEndereco().getRua());
            endereco.setCidade(usuarioComumDTO.getEndereco().getCidade());
            endereco.setEstado(usuarioComumDTO.getEndereco().getEstado());
            endereco.setCep(usuarioComumDTO.getEndereco().getCep());
            usuarioComum.setEndereco(endereco);
        }

        try {
            return usuarioComumRepository.save(usuarioComum);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já esta em uso.");
        }

    }

    public UsuarioComum buscaPorEmail(UsuarioComumDTO usuarioComumDTO){
        UsuarioComum usuarioComum = usuarioComumRepository.findByEmail(usuarioComumDTO.getEmail());
        if (usuarioComum == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não cadastrado!");
        }
        return usuarioComum;
    }

    public UsuarioComum editarUsuario(Long id, UsuarioComumDTO usuarioComumDTO){
        Optional<UsuarioComum> usuarioComumOptional = usuarioComumRepository.findById(id);
        Optional<UsuarioMedico> usuarioEmailDuplicado = Optional.ofNullable(usuarioMedicoRepository.findByEmail(usuarioComumDTO.getEmail()));

        if (usuarioComumOptional.isPresent()) {
            if (usuarioEmailDuplicado.isPresent()) {
                if (!usuarioComumOptional.get().getEmail().equals(usuarioEmailDuplicado.get().getEmail())){
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email já em uso, impossivel auteração!");
                }
            }
            UsuarioComum usuarioComum = usuarioComumOptional.get();
            usuarioComum.setNomes(usuarioComumDTO.getNomes());
            usuarioComum.setEmail(usuarioComumDTO.getEmail());
            usuarioComum.setIdade(usuarioComumDTO.getIdade());
            usuarioComum.setSexo(usuarioComumDTO.getSexo());

            if (usuarioComumDTO.getSenha() != null && !usuarioComumDTO.getSenha().isEmpty()) {
//                usuarioComum.setSenha(passwordEncoder.encode(usuarioComumDTO.getSenha()));
                usuarioComum.setSenha(usuarioComumDTO.getSenha());
            }
            if (usuarioComumDTO.getEndereco() != null) {
                Endereco endereco = usuarioComum.getEndereco();
                if (endereco == null) {
                    endereco = new Endereco();
                }
                endereco.setRua(usuarioComumDTO.getEndereco().getRua());
                endereco.setCidade(usuarioComumDTO.getEndereco().getCidade());
                endereco.setEstado(usuarioComumDTO.getEndereco().getEstado());
                endereco.setCep(usuarioComumDTO.getEndereco().getCep());
                endereco.setUsuario(usuarioComum.getEndereco().getUsuario());
                usuarioComum.setEndereco(endereco);
            }
            return usuarioComumRepository.save(usuarioComum);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado ou não cadastrado!");
    }

    public Boolean deletarUsuario(Long id) {
        Optional<UsuarioComum> usuarioComumOptional = usuarioComumRepository.findById(id);
         if (usuarioComumOptional.isPresent()) {
             usuarioComumRepository.deleteById(id);
             return true;
         }
         return false;
    }
}
