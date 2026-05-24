package com.proyectoaduana.msdetalleproducto.Repository;

import com.proyectoaduana.msdetalleproducto.Model.DetalleProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DetalleProductoRepository extends JpaRepository<DetalleProducto,Integer>{

    List<DetalleProducto> findByIdDeclaracion(Integer idDeclaracion);

    List<DetalleProducto> findByCategoria(String categoria);

}
