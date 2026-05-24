package com.proyectoaduana.msdeclaracionjurada.Repository;

import com.proyectoaduana.msdeclaracionjurada.Model.DeclaracionJurada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeclaracionJuradaRepository extends JpaRepository<DeclaracionJurada, Integer> {
    List<DeclaracionJurada> findByIdTramite(Integer idTramite);
}