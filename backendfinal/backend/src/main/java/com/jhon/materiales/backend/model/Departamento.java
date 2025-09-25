package com.jhon.materiales.backend.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Departamentos")
@Data @NoArgsConstructor @AllArgsConstructor
public class Departamento {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100, unique = true)
    private String nombre;
}
