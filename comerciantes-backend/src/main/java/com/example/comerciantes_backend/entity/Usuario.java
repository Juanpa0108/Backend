package com.example.comerciantes_backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

    @Id
    @Column(name = "id_usuario")
    private Long id; 

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "correo_electronico", length = 255, nullable = false, unique = true)
    private String correoElectronico;

    @Column(name = "contraseña", length = 255, nullable = false)
    private String contrasena;

    @Column(name = "rol", nullable = false)
    private String rol;


   

}
