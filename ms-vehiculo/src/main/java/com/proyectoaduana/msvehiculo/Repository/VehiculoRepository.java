package com.proyectoaduana.msvehiculo.Repository;

import com.proyectoaduana.msvehiculo.Model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {
    List<Vehiculo> findByRutDueno(String rutDueno);
    List<Vehiculo> findByMarca(String marca);
}
