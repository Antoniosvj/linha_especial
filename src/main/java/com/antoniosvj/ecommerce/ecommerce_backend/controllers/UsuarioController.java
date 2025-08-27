package com.antoniosvj.ecommerce.ecommerce_backend.controllers;

import com.antoniosvj.ecommerce.ecommerce_backend.entidade.Usuario;
import com.antoniosvj.ecommerce.ecommerce_backend.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario) {
        // Lógica de validação e criptografia de senha virá aqui
        // Por enquanto, vamos apenas salvar
        Optional<Usuario> usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());
        
        if (usuarioExistente.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT); // 409 Conflict
        }

        //criptografar senha antes de salvar
        String senhaCriptografada = passwordEncoder.encode(usuario.getSenhaHash());
        usuario.setSenhaHash(senhaCriptografada);

        Usuario novoUsuario = usuarioRepository.save(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED); // 201 Created
    }

    // O método de login será implementado mais tarde, com Spring Security
}