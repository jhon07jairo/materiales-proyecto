package com.jhon.materiales.backend.repository;

import com.jhon.materiales.backend.model.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

    // Ejemplo: buscar un departamento por nombre
    Departamento findByNombre(String nombre);
}
