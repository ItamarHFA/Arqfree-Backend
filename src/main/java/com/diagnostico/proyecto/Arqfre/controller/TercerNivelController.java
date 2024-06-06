package com.diagnostico.proyecto.Arqfre.controller;

import com.diagnostico.proyecto.Arqfre.model.TercerNivel;
import com.diagnostico.proyecto.Arqfre.service.ITercerNivelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tercerNivel")
public class TercerNivelController {

    private final ITercerNivelService service;
    @GetMapping
    public ResponseEntity<List<TercerNivel>> listarEstiloFachada(){
        List<TercerNivel> lista = service.listar();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
}
