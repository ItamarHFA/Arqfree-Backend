package com.diagnostico.proyecto.Arqfre.service.impl;


import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import com.diagnostico.proyecto.Arqfre.repository.IProyectoRepo;
import com.diagnostico.proyecto.Arqfre.service.IProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProyectoServiceImpl implements IProyectoService {


    private final IProyectoRepo repo;
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
    @Transactional
    @Override
    public List<Proyecto> obtenerProyectosDeHoy() {
        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(LocalTime.MAX);
        List<Proyecto> proyectos = repo.findAllByFechaCreacionWithPrimerNivel(startOfDay, endOfDay);
        proyectos.forEach(p -> {
            p.setSegundoNivel(repo.findAllByFechaCreacionWithSegundoNivel(startOfDay, endOfDay).stream()
                    .filter(pr -> pr.getId().equals(p.getId()))
                    .findFirst()
                    .orElse(p).getSegundoNivel());
            p.setTercerNivel(repo.findAllByFechaCreacionWithTercerNivel(startOfDay, endOfDay).stream()
                    .filter(pr -> pr.getId().equals(p.getId()))
                    .findFirst()
                    .orElse(p).getTercerNivel());
        });
        return proyectos;
    }
}
