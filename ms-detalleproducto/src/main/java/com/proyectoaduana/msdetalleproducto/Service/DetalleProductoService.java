package com.proyectoaduana.msdetalleproducto.Service;

import com.proyectoaduana.msdetalleproducto.Model.DetalleProducto;
import com.proyectoaduana.msdetalleproducto.Repository.DetalleProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class DetalleProductoService {
    @Autowired
    private DetalleProductoRepository detalleProductoRepository;
    private static final Logger log = LoggerFactory.getLogger(DetalleProductoService.class);

    public List<DetalleProducto> listarDetalles() {
        log.info("Listando todos los detalles de producto");
        return detalleProductoRepository.findAll();
    }

    public DetalleProducto buscarPorId(Integer id) {
        return detalleProductoRepository.findById(id).orElse(null);
    }

    public List<DetalleProducto> buscarPorDeclaracion(Integer idDeclaracion) {
        return detalleProductoRepository.findByIdDeclaracion(idDeclaracion);
    }

    public List<DetalleProducto> buscarPorCategoria(String categoria) {
        return detalleProductoRepository.findByCategoria(categoria);
    }

    public DetalleProducto agregarDetalle(DetalleProducto nuevo) {
        try {
        log.info("Creando detalle producto: {}", nuevo.getCategoria());
        nuevo.setIditem(null);
        return detalleProductoRepository.save(nuevo);
    }catch (Exception e) {
            log.error("Error al crear detalle producto: {}", e.getMessage());
            return null;
        }
    }

    public boolean eliminarDetalle(Integer id) {
        try {
        if (detalleProductoRepository.existsById(id)) {
            log.info("Eliminando detalle producto : {}", id);
            detalleProductoRepository.deleteById(id);
            return true;
        } else {
            log.warn("detalle producto {} no encontrado", id);
            return false;
        }
        } catch (Exception e) {
            log.error("Error al eliminar detalle producto con id {}: {}", id, e.getMessage());
            return false;
        }
    }


    public DetalleProducto actualizarDetalle(Integer id, DetalleProducto nuevo) {
        try {
        if (detalleProductoRepository.existsById(id)) {
            DetalleProducto detalle = detalleProductoRepository.findById(id).get();
            detalle.setCategoria(nuevo.getCategoria());
            detalle.setDescripcion(nuevo.getDescripcion());
            detalle.setIdDeclaracion(nuevo.getIdDeclaracion());
            detalleProductoRepository.save(detalle);
            return detalle;
        } else {
            return null;

        }
        } catch (Exception e) {
            log.error("Error al actualizar detalle producto con id {}: {}", id, e.getMessage());
            return null;
        }

    }
}