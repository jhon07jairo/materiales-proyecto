package com.jhon.materiales.backend.service;

import com.jhon.materiales.backend.model.Ciudad;
import java.util.List;

public interface CiudadService {
    List<Ciudad> listarTodos();
    Ciudad buscarPorId(Integer id);
    Ciudad guardar(Ciudad ciudad);
    void eliminar(Integer id);
}