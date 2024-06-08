package com.diagnostico.proyecto.Arqfre.service;


import com.diagnostico.proyecto.Arqfre.model.Proyecto;

import java.util.List;

public interface IProyectoService extends ICRUD<Proyecto>{
     List<Proyecto> obtenerProyectosDeHoy();

}
