package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.EstiloFachada;
import com.diagnostico.proyecto.Arqfre.model.Piso;
import com.diagnostico.proyecto.Arqfre.service.IEstiloFachadaService;
import com.diagnostico.proyecto.Arqfre.service.IPisoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/piso")
public class PisoController {
    private final IPisoService service;
    @GetMapping
    public ResponseEntity<List<Piso>> listarEstiloFachada(){
        List<Piso> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
