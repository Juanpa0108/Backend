package com.example.comerciantes_backend.repository;

import com.example.comerciantes_backend.entity.Establecimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EstablecimientoRepository extends JpaRepository<Establecimiento, Long> {
    List<Establecimiento> findByComercianteId(Long comercianteId);
}
