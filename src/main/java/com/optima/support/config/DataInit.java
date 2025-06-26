package com.optima.support.config;

import com.optima.support.model.Usuario;
import com.optima.support.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.annotation.PostConstruct;

@Configuration
public class DataInit {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void criarUsuarioDeTeste() {
        // Só cria se ainda não existir
        if (usuarioRepository.findByEmail("teste@email.com").isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setEmail("teste@email.com");
            usuario.setSenha(passwordEncoder.encode("123456")); // senha criptografada
            usuarioRepository.save(usuario);
        }
    }
}
