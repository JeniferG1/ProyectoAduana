package com.proyectoaduana.mspais.Repository;

import com.proyectoaduana.mspais.Model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
    Pais findByNombrePais(String nombrePais);
}