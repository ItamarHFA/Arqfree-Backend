package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.PrimerNivel;
import com.diagnostico.proyecto.Arqfre.repository.IPrimerNivelRepo;
import com.diagnostico.proyecto.Arqfre.service.IPrimerNivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PrimerNivelServiceImpl implements IPrimerNivelService {
    @Autowired
    private IPrimerNivelRepo repo;

    @Override
    public PrimerNivel registrar(PrimerNivel primerNivel) {
        return null;
    }

    @Override
    public PrimerNivel modificar(PrimerNivel primerNivel) {
        return null;
    }

    @Override
    public PrimerNivel leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<PrimerNivel> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
