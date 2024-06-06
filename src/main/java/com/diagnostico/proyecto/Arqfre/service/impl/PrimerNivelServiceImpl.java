package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.PrimerNivel;
import com.diagnostico.proyecto.Arqfre.repository.IPrimerNivelRepo;
import com.diagnostico.proyecto.Arqfre.service.IPrimerNivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class PrimerNivelServiceImpl implements IPrimerNivelService {

    private final IPrimerNivelRepo repo;

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
