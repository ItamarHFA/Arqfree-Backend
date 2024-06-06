package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.EstiloFachada;
import com.diagnostico.proyecto.Arqfre.repository.IEstiloFachadaRepo;
import com.diagnostico.proyecto.Arqfre.repository.IPisoRepo;
import com.diagnostico.proyecto.Arqfre.service.IEstiloFachadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class EstiloFachadaServiceImpl implements IEstiloFachadaService {

    private final IEstiloFachadaRepo repo;
    @Override
    public EstiloFachada registrar(EstiloFachada estiloFachada) {
        return null;
    }

    @Override
    public EstiloFachada modificar(EstiloFachada estiloFachada) {
        return null;
    }

    @Override
    public EstiloFachada leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<EstiloFachada> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
