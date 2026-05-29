package com.proyectoaduana.mspermisocirculacion.Model;

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
@Table(name = "permiso_circulacion")
public class PermisoCirculacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_permiso")
    private Integer idPermiso;


    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    @Past(message = "La fecha de emisión debe ser una fecha pasada")
    private Date fechaEmision;

    @Min(value = 1, message = "Los días de vigencia deben ser mayor a 0")
    @Column(name = "dias_vigencia")
    private Integer diasVigencia;

    @NotNull(message = "El id del trámite es obligatorio")
    @Column(name = "id_tramite", nullable = false)
    private Integer idTramite;

    @NotBlank(message = "La patente es obligatoria")
    @Size(max = 15, message = "La patente no puede superar 15 caracteres")
    @Column(name = "patente", nullable = false, length = 15)
    private String patente;
}