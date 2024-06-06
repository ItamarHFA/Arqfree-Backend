package com.diagnostico.proyecto.Arqfre.model;

import lombok.Data;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="proycto")
@Data
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_proyecto")
    private Long idProyecto;

    @Column(name = "tipo_proyecto", nullable = false)
    private String tipoProyecto;

    @Column(name = "area", nullable = false)
    private String area;

    @Column(name = "pisos", nullable = false)
    private String pisos;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "proyecto", cascade = CascadeType.ALL)
    private List<Necesidad> necesidades;

}
