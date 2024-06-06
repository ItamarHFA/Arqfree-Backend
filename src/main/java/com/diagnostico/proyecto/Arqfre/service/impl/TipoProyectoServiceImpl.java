package com.diagnostico.proyecto.Arqfre.service.impl;

import com.diagnostico.proyecto.Arqfre.model.TipoProyecto;
import com.diagnostico.proyecto.Arqfre.repository.ITipoProyectoRepo;
import com.diagnostico.proyecto.Arqfre.service.ITipoProyectoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TipoProyectoServiceImpl implements ITipoProyectoService {

    @Autowired
    private ITipoProyectoRepo repo;
    @Override
    public TipoProyecto registrar(TipoProyecto tipoProyecto) {
        return null;
    }

    @Override
    public TipoProyecto modificar(TipoProyecto tipoProyecto) {
        return null;
    }

    @Override
    public TipoProyecto leerPorId(Integer id) {
        return null;
    }

    @Override
    public List<TipoProyecto> listar() {
        return repo.findAll();
    }

    @Override
    public void eliminar(Integer id) {

    }
}
