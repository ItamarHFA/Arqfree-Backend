package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.SegundoNivel;
import com.diagnostico.proyecto.Arqfre.repository.ISegundoNivelRepo;
import com.diagnostico.proyecto.Arqfre.service.ISegundoNivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class SegundoNivelServiceImpl implements ISegundoNivelService {


    private final ISegundoNivelRepo repo;
    @Override
    public SegundoNivel registrar(SegundoNivel segundoNivel) {
        return null;
    }

    @Override
    public SegundoNivel modificar(SegundoNivel segundoNivel) {
        return null;
    }

    @Override
    public SegundoNivel leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<SegundoNivel> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
