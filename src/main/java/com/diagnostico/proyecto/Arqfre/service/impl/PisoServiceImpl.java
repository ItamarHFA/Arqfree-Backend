package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.Piso;
import com.diagnostico.proyecto.Arqfre.repository.IPisoRepo;
import com.diagnostico.proyecto.Arqfre.service.IPisoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PisoServiceImpl implements IPisoService {

    @Autowired
    private IPisoRepo repo;
    @Override
    public Piso registrar(Piso piso) {
        return null;
    }

    @Override
    public Piso modificar(Piso piso) {
        return null;
    }

    @Override
    public Piso leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<Piso> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
