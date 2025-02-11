package com.example.comerciantes_backend.service;

import com.example.comerciantes_backend.entity.Usuario;
import com.example.comerciantes_backend.repository.UsuarioRepository;
import com.example.comerciantes_backend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UsuarioRepository usuarioRepository, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.jwtUtil = jwtUtil;
    }

    public String autenticarUsuario(String correo, String contrasena) {
            try {
                Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoElectronico(correo);

                if (usuarioOptional.isEmpty()) {
                    throw new RuntimeException("Usuario no encontrado");
                }

                Usuario usuario = usuarioOptional.get();

                if (!usuario.getContrasena().equals(contrasena)) {
                    throw new RuntimeException("Contraseña incorrecta");
                }

                String jwt = jwtUtil.generateToken(usuario.getId(), usuario.getRol());
                return jwt;
            } catch (Exception e) {
                throw new RuntimeException("Error en la autenticación: " + e.getMessage());
            }
    }
}
