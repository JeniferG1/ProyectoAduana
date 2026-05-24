package com.proyectoaduana.msvehiculo.Service;

import com.proyectoaduana.msvehiculo.Model.Vehiculo;
import com.proyectoaduana.msvehiculo.Repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;
    private static final Logger log = LoggerFactory.getLogger(VehiculoService.class);

    public List<Vehiculo> listarVehiculos() {
        log.info("Listando todos los vehiculos ");
        return vehiculoRepository.findAll();
    }

    public Vehiculo buscarPorPatente(String patente) {
        return vehiculoRepository.findById(patente).orElse(null);
    }

    public List<Vehiculo> buscarPorRutDueno(String rutDueno) {
        return vehiculoRepository.findByRutDueno(rutDueno);
    }

    public List<Vehiculo> buscarPorMarca(String marca) {
        return vehiculoRepository.findByMarca(marca);
    }

    public Vehiculo agregarVehiculo(Vehiculo nuevo) {
        log.info("Creando usuario : {}", nuevo.getTipoPlaca());

        return vehiculoRepository.save(nuevo);
    }

    public boolean eliminarVehiculo(String patente) {
        if (vehiculoRepository.existsById(patente)) {
            log.info("Eliminando vehiculo : {}", patente);
            vehiculoRepository.deleteById(patente);
            return true;
        } else {
            log.warn("vehiculo {} no encontrado", patente);
            return false;
        }
    }

    public Vehiculo actualizarVehiculo(String patente, Vehiculo nuevo) {
        if (vehiculoRepository.existsById(patente)) {
            Vehiculo vehiculo = vehiculoRepository.findById(patente).get();
            vehiculo.setMarca(nuevo.getMarca());
            vehiculo.setModelo(nuevo.getModelo());
            vehiculo.setTipoPlaca(nuevo.getTipoPlaca());
            vehiculo.setRutDueno(nuevo.getRutDueno());
            vehiculoRepository.save(vehiculo);
            return vehiculo;
        } else {
            return null;
        }
    }
}