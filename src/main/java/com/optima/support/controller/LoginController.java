package com.optima.support.controller;

import com.optima.support.dto.LoginDTO;
import com.optima.support.service.AutenticacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AutenticacaoService autenticacaoService;

    public LoginController(AutenticacaoService autenticacaoService) {
        this.autenticacaoService = autenticacaoService;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {
        String token = autenticacaoService.autenticar(loginDTO.getEmail(), loginDTO.getSenha());
        return ResponseEntity.ok(token);
    }
}
