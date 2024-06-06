package com.diagnostico.proyecto.Arqfre.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="segundo_nivel")
@Data
public class SegundoNivel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;
}
