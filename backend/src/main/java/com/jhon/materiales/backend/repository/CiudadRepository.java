package com.jhon.materiales.backend.repository;

import com.jhon.materiales.backend.model.Ciudad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CiudadRepository extends JpaRepository<Ciudad, Integer> {
}
