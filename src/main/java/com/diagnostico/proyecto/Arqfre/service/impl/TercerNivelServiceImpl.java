package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.TercerNivel;
import com.diagnostico.proyecto.Arqfre.repository.ITercerNivelRepo;
import com.diagnostico.proyecto.Arqfre.service.ITercerNivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TercerNivelServiceImpl implements ITercerNivelService {

    @Autowired
    private ITercerNivelRepo repo;
    @Override
    public TercerNivel registrar(TercerNivel tercerNivel) {
        return null;
    }

    @Override
    public TercerNivel modificar(TercerNivel tercerNivel) {
        return null;
    }

    @Override
    public TercerNivel leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<TercerNivel> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
