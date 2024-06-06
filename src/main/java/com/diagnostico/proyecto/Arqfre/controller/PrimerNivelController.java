package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.PrimerNivel;
import com.diagnostico.proyecto.Arqfre.service.IPrimerNivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/primerNivel")
public class PrimerNivelController {
    private final IPrimerNivelService service;
    @GetMapping
    public ResponseEntity<List<PrimerNivel>> listarEstiloFachada(){
        List<PrimerNivel> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
