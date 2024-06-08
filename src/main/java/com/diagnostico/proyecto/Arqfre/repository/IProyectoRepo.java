package com.diagnostico.proyecto.Arqfre.repository;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IProyectoRepo extends JpaRepository<Proyecto, Long> {

    @Query("SELECT DISTINCT p FROM Proyecto p LEFT JOIN FETCH p.primerNivel WHERE p.fechaCreacion BETWEEN :startOfDay AND :endOfDay")
    List<Proyecto> findAllByFechaCreacionWithPrimerNivel(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT DISTINCT p FROM Proyecto p LEFT JOIN FETCH p.segundoNivel WHERE p.fechaCreacion BETWEEN :startOfDay AND :endOfDay")
    List<Proyecto> findAllByFechaCreacionWithSegundoNivel(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT DISTINCT p FROM Proyecto p LEFT JOIN FETCH p.tercerNivel WHERE p.fechaCreacion BETWEEN :startOfDay AND :endOfDay")
    List<Proyecto> findAllByFechaCreacionWithTercerNivel(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);



}
