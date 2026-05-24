package com.proyectoaduana.mspermisocirculacion.Repository;

import com.proyectoaduana.mspermisocirculacion.Model.PermisoCirculacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermisoCirculacionRepository extends JpaRepository<PermisoCirculacion, Integer> {
    List<PermisoCirculacion> findByPatente(String patente);
    List<PermisoCirculacion> findByIdTramite(Integer idTramite);
}
