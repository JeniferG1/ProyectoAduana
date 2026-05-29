package com.proyectoaduana.mspersona.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_persona")
    private Integer idPersona;

    @NotBlank(message = "El RUT/Pasaporte es obligatorio")
    @Size(max = 20, message = "El RUT/Pasaporte no puede superar 20 caracteres")
    @Column(name = "rut_pasaporte", nullable = false, unique = true, length = 20)
    private String rutPasaporte;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 10,max = 100)
    @Column(name = "nombres", nullable = false, length = 100)
    private String nombres;



    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 10,max = 100)
    @Column(name = "apellidos", nullable = false, length = 100)
    private String apellidos;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    @NotNull(message = "El campo es_menor es obligatorio")
    @Column(name = "es_menor", nullable = false)
    private Integer esMenor;
}