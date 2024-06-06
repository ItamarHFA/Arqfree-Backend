package com.diagnostico.proyecto.Arqfre.repository;

import com.diagnostico.proyecto.Arqfre.model.TipoProyecto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITipoProyectoRepo extends JpaRepository<TipoProyecto, Long> {
}
