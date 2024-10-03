package br.com.apifoodeducation.service;

//import br.com.apifoodeducation.model.Usuario;
//import br.com.apifoodeducation.repository.UsuarioRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import br.com.apifoodeducation.model.Role;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

//@Service
public class UsuarioDetailsService
//        implements UserDetailsService
{

//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Usuario usuario = usuarioRepository.findByEmail(email);
//        if (usuario == null) {
//            throw new UsernameNotFoundException("Usuário não encontrado com email: " + email);
//        }
//
//        Set<String> roles = usuario.getRoles().stream()
//                .map(Role::name)
//                .collect(Collectors.toSet());
//
//        return User.builder()
//                .username(usuario.getEmail())
//                .password(usuario.getSenha())
//                .roles(roles.toArray(new String[0]))
//                .build();
//    }
//
//    private Set<GrantedAuthority> getAuthorities(Usuario usuario) {
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        // Adicionar os papéis/roles do usuário
//        authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRoles())); // Exemplo
//        return authorities;
//    }
}
