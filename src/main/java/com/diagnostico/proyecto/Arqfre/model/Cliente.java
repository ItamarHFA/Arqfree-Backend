package com.diagnostico.proyecto.Arqfre.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="cliente")
@Data
public class Cliente  implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cliente")
    private Long idCliente;

    @Column(name = "nombre_cliente", nullable = false)
    private String nombreCliente;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "codigo_pais")
    private String codigoPais;

    @Column(name = "correo")
    private String correo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Proyecto> proyectos;

    @OneToOne(mappedBy = "cliente", cascade = CascadeType.ALL)
    private Preferencia preferencia;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<EstiloFachada> estilosFachada;

}
