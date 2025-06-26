package com.optima.support.controller;

import com.optima.support.model.Usuario;
import com.optima.support.service.UsuarioService;  // trocar para UsuarioService
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;  // tipo corrigido

    public UsuarioController(UsuarioService usuarioService) {  // construtor corrigido
        this.usuarioService = usuarioService;
    }

    // Lista todos os usuários
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        List<Usuario> usuarios = usuarioService.listarTodos();
        usuarios.forEach(u -> u.setSenha(null)); // Remove a senha do retorno
        return ResponseEntity.ok(usuarios);
    }

    // Busca um usuário por ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(usuario -> {
                    usuario.setSenha(null); // aqui era setPassword, corrigi para setSenha
                    return ResponseEntity.ok(usuario);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Cria um novo usuário
    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario usuarioSalvo = usuarioService.salvar(usuario);
        usuarioSalvo.setSenha(null); // Remove senha do retorno
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    // Atualiza um usuário
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario dadosAtualizados) {
        Usuario atualizado = usuarioService.atualizar(id, dadosAtualizados);
        if (atualizado == null) {
            return ResponseEntity.notFound().build();
        }
        atualizado.setSenha(null); // Remove senha do retorno
        return ResponseEntity.ok(atualizado);
    }

    // Deleta um usuário
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        boolean deletado = usuarioService.deletar(id);
        if (deletado) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
