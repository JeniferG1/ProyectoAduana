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
    @NotNull(message = "El id del permiso es obligatorio")
    @Column(name = "id_permiso", nullable = false)
    private Integer idPermiso;

    @Column(name = "fecha_emision")
    @Temporal(TemporalType.DATE)
    @Past(message = "La fecha de emisión debe ser una fecha pasada")
    private Date fechaEmision;

    @Min(value = 1, message = "Los días de vigencia deben ser mayor a 0")
    @Column(name = "dias_vigencia")
    private Integer diasVigencia;

    @Column(name = "id_tramite")
    private Integer idTramite;

    @Size(max = 15, message = "La patente no puede superar 15 caracteres")
    @Column(name = "patente", length = 15)
    private String patente;
}