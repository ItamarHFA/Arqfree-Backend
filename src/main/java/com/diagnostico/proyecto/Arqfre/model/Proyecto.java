package com.diagnostico.proyecto.Arqfre.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="proyecto")
@Data
public class Proyecto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "proyecto_id")
    private Long id;

    @Column(name = "Nombre_Cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "Numero_Celular", nullable = false)
    private String numeroCelular;

    @Column(name = "correo", nullable = false)
    private String correo;

    @ManyToOne
    @JoinColumn(name = "tipoProyecto_id", foreignKey = @ForeignKey(name = "fk_proyecto_tipoProyecto"))
    private TipoProyecto tipoProyecto;

    @Column(name = "Area", nullable = false)
    private String area;

    @ManyToOne
    @JoinColumn(name = "piso_id", foreignKey = @ForeignKey(name = "fk_proyecto_piso"))
    private Piso piso;

    @ManyToOne
    @JoinColumn(name = "estiloFachada_id", foreignKey = @ForeignKey(name = "fk_proyecto_estiloFachada"))
    private EstiloFachada estiloFachada;

    @Column(name = "Numero_Integrantes", nullable = false)
    private Integer numeroIntegrantes;

    @Column(name = "Colores_Favoritos", nullable = false)
    private String coloresFavoritos;

    @Column(name = "Mascota", nullable = false)
    private String mascota;

    @Column(name = "Espacio_Favorito", nullable = false)
    private String espacioFavorito;

    @Column(name = "Automovil", nullable = false)
    private String automovil;

    @Column(name = "Referencia_Vivienda", nullable = false)
    private String referenciaVivienda;

    @Column(name = "Otros_Primer_Nivel")
    private String otrosPrimerNivel;

    @Column(name = "Otros_Segundo_Nivel")
    private String otrosSegundoNivel;

    @Column(name = "Otros_Tercer_Nivel")
    private String otrosTercerNivel;


    @BatchSize(size = 10)
    @ManyToMany
    @JoinTable(
            name = "Proyecto_Primer_nivel",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "primer_nivel_id")
    )
    private List<PrimerNivel> primerNivel;
    //private Set<PrimerNivel> primerNivel; el mismo resultado

    @BatchSize(size = 10)
    @ManyToMany
    //@JsonBackReference
    @JoinTable(
            name = "proyecto_segundo_nivel",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "segundo_nivel_id")
    )
    private List<SegundoNivel> segundoNivel;



    @BatchSize(size = 10)
    @ManyToMany

    @JoinTable(
            name = "proyecto_tercer_nivel",
            joinColumns = @JoinColumn(name = "proyecto_id"),
            inverseJoinColumns = @JoinColumn(name = "tercer_mivel_id")
    )
    private List<TercerNivel> tercerNivel;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm a")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm a")
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }
}
