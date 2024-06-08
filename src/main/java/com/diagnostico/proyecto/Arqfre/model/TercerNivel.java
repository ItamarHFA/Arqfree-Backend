package com.diagnostico.proyecto.Arqfre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="tercer_nivel")
@Data
public class TercerNivel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tercer_nivel_id")
    private Long id;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @JsonBackReference

    @ManyToMany(mappedBy = "tercerNivel")
    private List<Proyecto> proyectos;


}
