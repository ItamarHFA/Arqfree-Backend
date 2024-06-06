package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.TipoProyecto;
import com.diagnostico.proyecto.Arqfre.service.ITipoProyectoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tipoProyecto")
public class TipoProyectoController {

    private final ITipoProyectoService service;
    @GetMapping
    public ResponseEntity<List<TipoProyecto>> listarEstiloFachada(){
        List<TipoProyecto> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
