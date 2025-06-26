package com.optima.support.service;

import com.optima.support.model.Usuario;
import com.optima.support.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticacaoService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AutenticacaoService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Método para autenticar e retornar token (String)
    public String autenticar(String email, String senha) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

        if (usuarioOpt.isEmpty()) {
            throw new RuntimeException("Usuário não encontrado");
        }

        Usuario usuario = usuarioOpt.get();

        // Verifica a senha
        if (!passwordEncoder.matches(senha, usuario.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        // Aqui você pode implementar geração real de token JWT
        // Por enquanto, só retorna um string simulando token
        return "token_simulado_para_usuario_" + usuario.getId();
    }
}
