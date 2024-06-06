package com.diagnostico.proyecto.Arqfre.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="preferencia")
@Data
public class Preferencia implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_preferencia")
    private Long idPreferencia;

    @Column(name = "num_integrantes", nullable = false)
    private Integer numIntegrantes;

    @Column(name = "colores_favoritos", nullable = false)
    private String coloresFavoritos;

    @Column(name = "mascotas", nullable = false)
    private String mascotas;

    @Column(name = "espacios_favoritos", nullable = false)
    private String espaciosFavoritos;

    @Column(name = "modelo_automovil", nullable = false)
    private String modeloAutomovil;

    @Column(name = "referencia_vivienda", nullable = false)
    private String referenciaVivienda;

    @OneToOne
    @JoinColumn(name = "id_cliente", nullable = false,foreignKey = @ForeignKey(name = "fk_preferencia_cliente"))
    private Cliente cliente;
}
