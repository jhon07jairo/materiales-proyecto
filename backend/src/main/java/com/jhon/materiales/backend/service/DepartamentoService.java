package com.jhon.materiales.backend.service;

import com.jhon.materiales.backend.model.Departamento;

import java.util.List;

public interface DepartamentoService {
    List<Departamento> listarTodos();
    Departamento buscarPorId(Integer id);
    Departamento guardar(Departamento departamento);
    void eliminar(Integer id);
}
