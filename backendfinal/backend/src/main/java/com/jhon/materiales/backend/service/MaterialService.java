package com.jhon.materiales.backend.service;

import com.jhon.materiales.backend.model.Material;

import java.time.LocalDate;
import java.util.List;

public interface MaterialService {

    List<Material> listarTodos();

    Material buscarPorId(Integer id);

    Material guardar(Material material);

    Material actualizar(Integer id, Material material);

    void eliminar(Integer id);

    List<Material> buscarPorTipo(String tipo);

    List<Material> buscarPorFechaCompra(LocalDate inicio, LocalDate fin);

    List<Material> buscarPorCiudad(Integer ciudadId);

    List<Material> buscarPorEstado(String estado);
}
