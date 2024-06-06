package com.diagnostico.proyecto.Arqfre.repository;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProyecto extends JpaRepository<Proyecto, Long> {
}
