package com.proyectoaduana.msvehiculo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehiculo")
public class Vehiculo {

    @Id
    @NotBlank(message = "La patente es obligatoria")
    @Size(max = 15)
    @Column(name = "patente", nullable = false, length = 15)
    private String patente;

    @Column(name = "marca", length = 50)
    private String marca;

    @Column(name = "modelo", length = 50)
    private String modelo;

    @Column(name = "tipo_placa", length = 20)
    private String tipoPlaca;

    @Column(name = "rut_dueno", length = 20)
    private String rutDueno;
}