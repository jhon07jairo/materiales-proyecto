package com.jhon.materiales.backend.controller;

import com.jhon.materiales.backend.model.Departamento;
import com.jhon.materiales.backend.service.DepartamentoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departamentos")
public class DepartamentoController {

    private final DepartamentoService departamentoService;

    public DepartamentoController(DepartamentoService departamentoService) {
        this.departamentoService = departamentoService;
    }

    @GetMapping
    public List<Departamento> listar() {
        return departamentoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Departamento buscarPorId(@PathVariable Integer id) {
        return departamentoService.buscarPorId(id);
    }

    @PostMapping
    public Departamento guardar(@RequestBody Departamento departamento) {
        return departamentoService.guardar(departamento);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        departamentoService.eliminar(id);
    }
}
