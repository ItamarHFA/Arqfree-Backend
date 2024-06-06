package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.SegundoNivel;
import com.diagnostico.proyecto.Arqfre.service.ISegundoNivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/segundoNivel")
public class SegundoNivelController {

    private final ISegundoNivelService service;
    @GetMapping
    public ResponseEntity<List<SegundoNivel>> listarEstiloFachada(){
        List<SegundoNivel> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
