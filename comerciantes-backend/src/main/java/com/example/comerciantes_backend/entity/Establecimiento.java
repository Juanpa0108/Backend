package com.example.comerciantes_backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Table(name = "Establecimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Establecimiento {

    @Id
    @Column(name = "id_establecimiento")
    private Long id; 

    @Column(name = "nombre", length = 255, nullable = false)
    private String nombre;

    @Column(name = "ingresos", nullable = false)
    private Double ingresos;

    @Column(name = "numero_empleados", nullable = false)
    private Integer numeroEmpleados;

    @ManyToOne
    @JoinColumn(name = "ID_COMERCIANTE", nullable = false)
    private Comerciante comerciante;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_actualizacion", nullable = false, columnDefinition = "DATE DEFAULT SYSDATE")
    private Date fechaActualizacion;

    @Column(name = "usuario_actualizacion", length = 50)
    private String usuarioActualizacion;
}
