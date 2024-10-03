package br.com.apifoodeducation.service;

import br.com.apifoodeducation.dto.UsuarioMedicoDTO;
import br.com.apifoodeducation.model.Endereco;
import br.com.apifoodeducation.model.Role;
import br.com.apifoodeducation.model.UsuarioComum;
import br.com.apifoodeducation.model.UsuarioMedico;
import br.com.apifoodeducation.repository.UsuarioComumRepository;
import br.com.apifoodeducation.repository.UsuarioMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioMedicoService {

    @Autowired
    private UsuarioMedicoRepository usuarioMedicoRepository;

    @Autowired
    private UsuarioComumRepository usuarioComumRepository;

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public UsuarioMedico savar(UsuarioMedicoDTO usuarioMedicoDTO){
        if (usuarioMedicoRepository.findByEmail(usuarioMedicoDTO.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já esta em uso");
        }else if (usuarioMedicoRepository.findByCrm(usuarioMedicoDTO.getCrm()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "CRM já esta em uso");
        }

        UsuarioMedico usuarioMedico = new UsuarioMedico();
        usuarioMedico.setNomes(usuarioMedicoDTO.getNomes());
        usuarioMedico.setEmail(usuarioMedicoDTO.getEmail());
//        usuarioComum.setSenha(passwordEncoder.encode(usuarioComumDTO.getSenha()));
        usuarioMedico.setSenha(usuarioMedicoDTO.getSenha());
        usuarioMedico.setIdade(usuarioMedicoDTO.getIdade());
        usuarioMedico.setSexo(usuarioMedicoDTO.getSexo());
        usuarioMedico.setCrm(usuarioMedicoDTO.getCrm());
        Set<Role> roles = new HashSet<>();
        roles.add(Role.ROLE_MEDICO);
        usuarioMedico.setRoles(roles);

        if(usuarioMedicoDTO.getEndereco() != null) {
            Endereco endereco = new Endereco();
            endereco.setRua(usuarioMedicoDTO.getEndereco().getRua());
            endereco.setCidade(usuarioMedicoDTO.getEndereco().getCidade());
            endereco.setEstado(usuarioMedicoDTO.getEndereco().getEstado());
            endereco.setCep(usuarioMedicoDTO.getEndereco().getCep());
            usuarioMedico.setEndereco(endereco);
        }

        try {
            return usuarioMedicoRepository.save(usuarioMedico);
        }catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já esta em uso.");
        }

    }

    public UsuarioMedico buscaPorEmail(UsuarioMedicoDTO usuarioMedicoDTO){
        UsuarioMedico usuarioMedico = usuarioMedicoRepository.findByEmail(usuarioMedicoDTO.getEmail());
        if (usuarioMedico == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email não cadastrado!");
        }
        return usuarioMedico;
    }

    public UsuarioMedico editarUsuario(Long id, UsuarioMedicoDTO usuarioMedicoDTO){
        Optional<UsuarioMedico> usuarioMedicoOptional = usuarioMedicoRepository.findById(id);
        Optional<UsuarioComum> usuarioEmailDuplicado = Optional.ofNullable(usuarioComumRepository.findByEmail(usuarioMedicoDTO.getEmail()));
        Optional<UsuarioMedico> usuarioMedicoDuplicado = Optional.ofNullable(usuarioMedicoRepository.findByEmail(usuarioMedicoDTO.getEmail()));
        Optional<UsuarioMedico> usuarioMedicoCRMDuplicado = Optional.ofNullable(usuarioMedicoRepository.findByCrm(usuarioMedicoDTO.getCrm()));

        if (usuarioMedicoOptional.isPresent()) {
            if (usuarioEmailDuplicado.isPresent()) {
                System.out.println(usuarioEmailDuplicado.get().getEmail() + "ID - " + usuarioEmailDuplicado.get().getId());
                if (usuarioMedicoOptional.get().getEmail().equals(usuarioEmailDuplicado.get().getEmail())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email já em uso, impossivel auteração!");
                }
            }
            if (usuarioMedicoDuplicado.isPresent()){
                System.out.println(usuarioMedicoDuplicado.get().getEmail() +
                        " ID - " + usuarioMedicoDuplicado.get().getId()  +
                         " CRM - " + usuarioMedicoDuplicado.get().getCrm());
                System.out.println(usuarioMedicoOptional.get().getEmail() +
                        " ID - " + usuarioMedicoOptional.get().getId() +
                         " CRM - " + usuarioMedicoOptional.get().getCrm());
                System.out.println("CRM Digitado - " + usuarioMedicoDTO.getCrm());
                if (!usuarioMedicoOptional.get().getId().equals(usuarioMedicoDuplicado.get().getId())) {
                     throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Email já em uso, impossivel auteração!");
                }
            }
            if (usuarioMedicoCRMDuplicado.isPresent()) {
                if (!usuarioMedicoOptional.get().getCrm().equals(usuarioMedicoCRMDuplicado.get().getCrm())) {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "CRM já em uso, impossivel auteração!");
                }
            }
            UsuarioMedico usuarioMedico = usuarioMedicoOptional.get();
            usuarioMedico.setNomes(usuarioMedicoDTO.getNomes());
            usuarioMedico.setEmail(usuarioMedicoDTO.getEmail());
            usuarioMedico.setIdade(usuarioMedicoDTO.getIdade());
            usuarioMedico.setSexo(usuarioMedicoDTO.getSexo());
            usuarioMedico.setCrm(usuarioMedicoDTO.getCrm());

            if (usuarioMedicoDTO.getSenha() != null && !usuarioMedicoDTO.getSenha().isEmpty()) {
//                usuarioMedico.setSenha(passwordEncoder.encode(usuarioComumDTO.getSenha()));
                usuarioMedico.setSenha(usuarioMedicoDTO.getSenha());
            }
            if (usuarioMedicoDTO.getEndereco() != null) {
                Endereco endereco = usuarioMedico.getEndereco();
                if (endereco == null) {
                    endereco = new Endereco();
                }
                endereco.setRua(usuarioMedicoDTO.getEndereco().getRua());
                endereco.setCidade(usuarioMedicoDTO.getEndereco().getCidade());
                endereco.setEstado(usuarioMedicoDTO.getEndereco().getEstado());
                endereco.setCep(usuarioMedicoDTO.getEndereco().getCep());
                endereco.setUsuario(usuarioMedico.getEndereco().getUsuario());
                usuarioMedico.setEndereco(endereco);
            }
            return usuarioMedicoRepository.save(usuarioMedico);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado ou não cadastrado!");
    }

    public Boolean deletarUsuario(Long id) {
        Optional<UsuarioMedico> usuarioMedicoOptional = usuarioMedicoRepository.findById(id);
         if (usuarioMedicoOptional.isPresent()) {
             usuarioMedicoRepository.deleteById(id);
             return true;
         }
         return false;
    }
}
