package com.jhon.materiales.backend.repository;

import com.jhon.materiales.backend.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    // 🔹 Buscar por tipo (ignora mayúsculas/minúsculas)
    List<Material> findByTipoIgnoreCase(String tipo);

    // 🔹 Buscar por rango de fechas de compra
    List<Material> findByFechaCompraBetween(LocalDate inicio, LocalDate fin);

    // 🔹 Buscar por ciudad (usando el id de la relación)
    List<Material> findByCiudad_Id(Integer ciudadId);

    // 🔹 Buscar por estado (ignora mayúsculas/minúsculas)
    List<Material> findByEstadoIgnoreCase(String estado);
}
