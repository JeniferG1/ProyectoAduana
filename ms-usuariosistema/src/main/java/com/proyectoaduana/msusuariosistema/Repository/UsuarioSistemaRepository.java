package com.proyectoaduana.msusuariosistema.Repository;

import com.proyectoaduana.msusuariosistema.Model.UsuarioSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioSistemaRepository extends JpaRepository<UsuarioSistema, Integer> {
    UsuarioSistema findByNombreUsuario(String nombreUsuario);
    List<UsuarioSistema> findByInstitucion(String institucion);
    List<UsuarioSistema> findByCuentaActiva(Integer cuentaActiva);
}
