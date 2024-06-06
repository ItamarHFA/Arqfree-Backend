package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.EstiloFachada;
import com.diagnostico.proyecto.Arqfre.service.IEstiloFachadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/estiloFachada")
public class EstiloFachadaController {
    private final IEstiloFachadaService service;
    @GetMapping
    public ResponseEntity<List<EstiloFachada>> listarEstiloFachada(){
        List<EstiloFachada> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
