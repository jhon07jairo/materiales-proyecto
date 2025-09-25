package com.jhon.materiales.backend.service.impl;

import com.jhon.materiales.backend.model.Material;
import com.jhon.materiales.backend.repository.MaterialRepository;
import com.jhon.materiales.backend.service.MaterialService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {

    private final MaterialRepository materialRepository;

    public MaterialServiceImpl(MaterialRepository materialRepository) {
        this.materialRepository = materialRepository;
    }

    @Override
    public List<Material> listarTodos() {
        return materialRepository.findAll();
    }

    @Override
    public Material buscarPorId(Integer id) {
        return materialRepository.findById(id).orElse(null);
    }

    @Override
    public Material guardar(Material material) {
        return materialRepository.save(material);
    }

    @Override
    public Material actualizar(Integer id, Material material) {
        Material existente = buscarPorId(id);

        // 🔹 Actualizamos solo los campos permitidos
        existente.setNombre(material.getNombre());
        existente.setDescripcion(material.getDescripcion());
        existente.setTipo(material.getTipo());
        existente.setPrecio(material.getPrecio());
        existente.setFechaCompra(material.getFechaCompra());
        existente.setFechaVenta(material.getFechaVenta());
        existente.setEstado(material.getEstado());
        existente.setCiudad(material.getCiudad());

        return materialRepository.save(existente);
    }

    @Override
    public void eliminar(Integer id) {
        materialRepository.deleteById(id);
    }

    @Override
    public List<Material> buscarPorTipo(String tipo) {
        return materialRepository.findByTipoIgnoreCase(tipo);
    }

    @Override
    public List<Material> buscarPorFechaCompra(LocalDate inicio, LocalDate fin) {
        return materialRepository.findByFechaCompraBetween(inicio, fin);
    }

    @Override
    public List<Material> buscarPorCiudad(Integer ciudadId) {
        return materialRepository.findByCiudad_Id(ciudadId);
    }

    @Override
    public List<Material> buscarPorEstado(String estado) {
        return materialRepository.findByEstadoIgnoreCase(estado);
    }
}
