package com.proyectoaduana.mspasofronterizo.Repository;

import com.proyectoaduana.mspasofronterizo.Model.PasoFronterizo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PasoFronterizoRepository extends JpaRepository<PasoFronterizo, Integer> {
    List<PasoFronterizo> findByCodPais(Integer codPais);
    PasoFronterizo findByNombrePaso(String nombrePaso);
}