package com.diagnostico.proyecto.Arqfre.repository;

import com.diagnostico.proyecto.Arqfre.model.Proyecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface IProyectoRepo extends JpaRepository<Proyecto, Long> {
    //@Query("SELECT p FROM Proyecto p WHERE DATE(p.fechaCreacion) = CURRENT_DATE")
    /*@Query("SELECT p FROM Proyecto p WHERE FUNCTION('DATE', p.fechaCreacion) = CURRENT_DATE")
    List<Proyecto> findAllByFechaCreacion();*/
    @Query("SELECT p FROM Proyecto p WHERE p.fechaCreacion BETWEEN :startOfDay AND :endOfDay")
    List<Proyecto> findAllByFechaCreacion(@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
