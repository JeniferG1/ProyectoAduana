package com.proyectoaduana.msrelacionfamiliar.Repository;

import com.proyectoaduana.msrelacionfamiliar.Model.RelacionFamiliar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelacionFamiliarRepository extends JpaRepository<RelacionFamiliar, Integer> {
    List<RelacionFamiliar> findByRutAdulto(String rutAdulto);
    List<RelacionFamiliar> findByRutMenor(String rutMenor);
}
