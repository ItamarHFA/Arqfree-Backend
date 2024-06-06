package com.diagnostico.proyecto.Arqfre.repository;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IProyectoRepo extends JpaRepository<Proyecto, Long> {
    @Query("SELECT p FROM Proyecto p WHERE DATE(p.fechaCreacion) = CURRENT_DATE")
    List<Proyecto> findAllByFechaCreacion();
}
