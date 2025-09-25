package com.jhon.materiales.backend.service.impl;

import com.jhon.materiales.backend.model.Departamento;
import com.jhon.materiales.backend.repository.DepartamentoRepository;
import com.jhon.materiales.backend.service.DepartamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    private final DepartamentoRepository departamentoRepository;

    public DepartamentoServiceImpl(DepartamentoRepository departamentoRepository) {
        this.departamentoRepository = departamentoRepository;
    }

    @Override
    public List<Departamento> listarTodos() {
        return departamentoRepository.findAll();
    }

    @Override
    public Departamento buscarPorId(Integer id) {
        return departamentoRepository.findById(id).orElse(null);
    }

    @Override
    public Departamento guardar(Departamento departamento) {
        return departamentoRepository.save(departamento);
    }

    @Override
    public void eliminar(Integer id) {
        departamentoRepository.deleteById(id);
    }
}
