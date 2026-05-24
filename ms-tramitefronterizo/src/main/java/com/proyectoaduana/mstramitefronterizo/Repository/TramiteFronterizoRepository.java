package com.proyectoaduana.mstramitefronterizo.Repository;

import com.proyectoaduana.mstramitefronterizo.Model.TramiteFronterizo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TramiteFronterizoRepository extends JpaRepository<TramiteFronterizo, Integer> {
    List<TramiteFronterizo> findByRutPasajero(String rutPasajero);
    List<TramiteFronterizo> findByIdPaso(Integer idPaso);
    List<TramiteFronterizo> findByIdFuncionario(Integer idFuncionario);
}
