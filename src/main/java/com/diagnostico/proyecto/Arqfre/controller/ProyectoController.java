package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.EstiloFachada;
import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import com.diagnostico.proyecto.Arqfre.service.IProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
