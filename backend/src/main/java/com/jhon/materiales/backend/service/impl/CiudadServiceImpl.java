package com.jhon.materiales.backend.service.impl;

import com.jhon.materiales.backend.model.Ciudad;
import com.jhon.materiales.backend.repository.CiudadRepository;
import com.jhon.materiales.backend.service.CiudadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CiudadServiceImpl implements CiudadService {

    private final CiudadRepository ciudadRepository;

    public CiudadServiceImpl(CiudadRepository ciudadRepository) {
        this.ciudadRepository = ciudadRepository;
    }

    @Override
    public List<Ciudad> listarTodos() {
        return ciudadRepository.findAll();
    }

    @Override
    public Ciudad buscarPorId(Integer id) {
        return ciudadRepository.findById(id).orElse(null);
    }

    @Override
    public Ciudad guardar(Ciudad ciudad) {
        return ciudadRepository.save(ciudad);
    }

    @Override
    public void eliminar(Integer id) {
        ciudadRepository.deleteById(id);
    }
}
