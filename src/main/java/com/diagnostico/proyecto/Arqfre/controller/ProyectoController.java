package com.diagnostico.proyecto.Arqfre.controller;



import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import com.diagnostico.proyecto.Arqfre.service.IProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/proyecto")
public class ProyectoController {

    private final IProyectoService service;
    @GetMapping
    public ResponseEntity<List<Proyecto>> listarEstiloFachada(){
        List<Proyecto> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Object> registrar(@RequestBody Proyecto pro) {
        Proyecto proyecto = service.registrar(pro);
        // localhost:8080/pacientes/1
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(proyecto.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/hoy")
    public List<Proyecto> obtenerProyectosDeHoy() {
        return service.obtenerProyectosDeHoy();
    }

}
