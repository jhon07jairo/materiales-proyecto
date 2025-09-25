package com.jhon.materiales.backend.controller;

import com.jhon.materiales.backend.model.Material;
import com.jhon.materiales.backend.service.MaterialService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/materiales")
@Tag(name = "Materiales", description = "Endpoints para la gestión de materiales")
public class MaterialController {

    private final MaterialService materialService;

    public MaterialController(MaterialService materialService) {
        this.materialService = materialService;
    }

    // 🔹 Listar todos los materiales
    @GetMapping
    @Operation(summary = "Listar materiales", description = "Obtiene todos los materiales registrados")
    public List<Material> listar() {
        return materialService.listarTodos();
    }

    // 🔹 Buscar material por ID
    @GetMapping("/{id}")
    @Operation(summary = "Buscar material por ID", description = "Devuelve un material según su identificador")
    public Material buscarPorId(
            @Parameter(description = "ID del material") @PathVariable Integer id) {
        return materialService.buscarPorId(id);
    }

    // 🔹 Crear material (POST)
    @PostMapping
    @Operation(summary = "Crear material", description = "Registra un nuevo material en la base de datos")
    public ResponseEntity<Material> crear(
            @Parameter(description = "Objeto Material en formato JSON") @RequestBody Material material) {
        if (material.getId() != null) {
            return ResponseEntity.badRequest().build(); // No debe venir con ID
        }
        return ResponseEntity.ok(materialService.guardar(material));
    }

    // 🔹 Actualizar material (PUT)
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar material", description = "Modifica un material existente usando su ID")
    public ResponseEntity<Material> actualizar(
            @Parameter(description = "ID del material a actualizar") @PathVariable Integer id,
            @Parameter(description = "Objeto Material con los cambios") @RequestBody Material material) {
        return ResponseEntity.ok(materialService.actualizar(id, material));
    }

    // 🔹 Eliminar material por ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar material", description = "Elimina un material de la base de datos por su ID")
    public void eliminar(
            @Parameter(description = "ID del material a eliminar") @PathVariable Integer id) {
        materialService.eliminar(id);
    }

    // 🔹 Buscar materiales por tipo
    @GetMapping("/tipo/{tipo}")
    @Operation(summary = "Buscar por tipo", description = "Filtra materiales según su tipo")
    public List<Material> buscarPorTipo(
            @Parameter(description = "Tipo de material (ejemplo: Construcción)") @PathVariable String tipo) {
        return materialService.buscarPorTipo(tipo);
    }

    // 🔹 Buscar materiales por rango de fechas de compra
    @GetMapping("/fecha-compra")
    @Operation(summary = "Buscar por rango de fechas de compra", description = "Obtiene materiales comprados entre dos fechas")
    public List<Material> buscarPorFechaCompra(
            @Parameter(description = "Fecha de inicio (formato: yyyy-MM-dd)")
            @RequestParam("inicio") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,

            @Parameter(description = "Fecha de fin (formato: yyyy-MM-dd)")
            @RequestParam("fin") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fin) {
        return materialService.buscarPorFechaCompra(inicio, fin);
    }

    // 🔹 Buscar materiales por ciudad
    @GetMapping("/ciudad/{ciudadId}")
    @Operation(summary = "Buscar por ciudad", description = "Filtra materiales según la ciudad a la que pertenecen")
    public List<Material> buscarPorCiudad(
            @Parameter(description = "ID de la ciudad") @PathVariable Integer ciudadId) {
        return materialService.buscarPorCiudad(ciudadId);
    }

    // 🔹 Buscar materiales por estado
    @GetMapping("/estado/{estado}")
    @Operation(summary = "Buscar por estado", description = "Filtra materiales según su estado (ACTIVO, DISPONIBLE, ASIGNADO)")
    public List<Material> buscarPorEstado(
            @Parameter(description = "Estado del material") @PathVariable String estado) {
        return materialService.buscarPorEstado(estado);
    }
}
