package com.jhon.materiales.backend.controller;

import com.jhon.materiales.backend.model.Ciudad;
import com.jhon.materiales.backend.service.CiudadService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ciudades")
public class CiudadController {

    private final CiudadService ciudadService;

    public CiudadController(CiudadService ciudadService) {
        this.ciudadService = ciudadService;
    }

    // GET: Listar todas las ciudades
    @GetMapping
    public List<Ciudad> listar() {
        return ciudadService.listarTodos();
    }

    // GET: Buscar ciudad por ID
    @GetMapping("/{id}")
    public Ciudad buscarPorId(@PathVariable Integer id) {
        return ciudadService.buscarPorId(id);
    }

    // POST: Guardar ciudad
    @PostMapping
    public Ciudad guardar(@RequestBody Ciudad ciudad) {
        return ciudadService.guardar(ciudad);
    }

    // DELETE: Eliminar ciudad por ID
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        ciudadService.eliminar(id);
    }
}
