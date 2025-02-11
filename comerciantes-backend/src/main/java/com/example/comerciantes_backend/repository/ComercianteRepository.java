package com.example.comerciantes_backend.repository;

import com.example.comerciantes_backend.dto.ComercianteCDTO;
import com.example.comerciantes_backend.entity.Comerciante;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ComercianteRepository extends JpaRepository<Comerciante, Long> {

    // Buscar por municipio
    List<Comerciante> findByMunicipio(String municipio);

    // Buscar por estado
    List<Comerciante> findByEstado(String estado);

    @SuppressWarnings("null")
    Optional<Comerciante> findById(Long id);

   
    @Query(value = "SELECT resultado_pipe FROM TABLE(obtener_comerciantes_activos())", nativeQuery = true)
    List<String> obtenerComerciantesActivos();


    

    boolean existsByCorreoElectronico(String correoElectronico);


    @Query("""
    SELECT new com.example.comerciantes_backend.dto.ComercianteCDTO(
        c.id, c.nombreRazonSocial, c.municipio, c.telefono, 
        c.correoElectronico, c.fechaRegistro, c.estado, 
        c.fechaActualizacion, c.usuarioActualizacion, COUNT(e.id)) 
    FROM Comerciante c 
    LEFT JOIN c.establecimientos e 
    WHERE (:nombre IS NULL OR LOWER(c.nombreRazonSocial) LIKE LOWER(CONCAT('%', :nombre, '%')))
    AND (:fechaRegistro IS NULL OR c.fechaRegistro = :fechaRegistro)
    AND (:estado IS NULL OR c.estado = :estado)
    GROUP BY c.id, c.nombreRazonSocial, c.municipio, c.telefono, 
        c.correoElectronico, c.fechaRegistro, c.estado, 
        c.fechaActualizacion, c.usuarioActualizacion
""")
Page<ComercianteCDTO> findByFiltersWithCount(@Param("nombre") String nombre, 
                                             @Param("fechaRegistro") Date fechaRegistro, 
                                             @Param("estado") String estado, 
                                             Pageable pageable);




}
