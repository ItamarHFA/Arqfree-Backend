package com.diagnostico.proyecto.Arqfre.model;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="necesidad")
@Data
public class Necesidad implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_Necesidad")
    private Long idNecesidad;

    @Column(name = "nivel", nullable = false)
    private String nivel;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "id_proyecto", nullable = false)
    private Proyecto proyecto;

}
