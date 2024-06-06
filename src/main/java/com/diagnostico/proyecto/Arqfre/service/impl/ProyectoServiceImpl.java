package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import com.diagnostico.proyecto.Arqfre.repository.IProyectoRepo;
import com.diagnostico.proyecto.Arqfre.service.IProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProyectoServiceImpl implements IProyectoService {

    @Autowired
    private IProyectoRepo repo;
    @Override
    public Proyecto registrar(Proyecto proyecto) {
        return repo.save(proyecto);
    }

    @Override
    public Proyecto modificar(Proyecto proyecto) {
        return null;
    }

    @Override
    public Proyecto leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<Proyecto> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
