package com.example.comerciantes_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Comerciantes", uniqueConstraints = {
    @UniqueConstraint(columnNames = "correo_electronico")
})
public class Comerciante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Asegúrate de usar IDENTITY
    @Column(name = "id_comerciante")
    private Long id; 

    @Column(name = "nombre_razon_social", length = 255, nullable = false)
    private String nombreRazonSocial;

    @Column(name = "municipio", length = 100, nullable = false)
    private String municipio;

    @Column(name = "telefono", length = 20)
    private String telefono;

    @Column(name = "correo_electronico", length = 255)
    private String correoElectronico;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_registro", nullable = false, updatable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    private Date fechaRegistro;

    @OneToMany(mappedBy = "comerciante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Establecimiento> establecimientos;

    @Column(name = "estado", length = 20, nullable = false)
    private String estado;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    private Date fechaActualizacion;

    @Column(name = "usuario_actualizacion", length = 50)
    private String usuarioActualizacion;

}
