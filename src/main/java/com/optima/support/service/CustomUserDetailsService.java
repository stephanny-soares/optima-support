package com.optima.support.security;

import com.optima.support.model.Usuario;
import com.optima.support.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco pelo email
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com email: " + email));

        // Converte seu usuário para o UserDetails do Spring Security usando o builder
        return User.builder()
                .username(usuario.getEmail())       // Define o username (email)
                .password(usuario.getPassword())    // Define a senha (criptografada!)
                .authorities("USER")                 // Define as roles/perfis (aqui só "USER" como exemplo)
                .build();                           // Cria o objeto UserDetails
    }
}
