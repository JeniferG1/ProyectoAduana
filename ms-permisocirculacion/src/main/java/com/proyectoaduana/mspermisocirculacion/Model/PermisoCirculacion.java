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
    @Column(name = "id_permiso")
    private Integer IdPermiso;


    @Column(name = "fecha_emision")
    private Date FechaEmision;

    @Column(name = "dias_vigencia")
    private Integer DiasVigencia;

    @Column(name = "patente", length = 15)
    private String Patente;


    @Column(name = "id_tramite")
    private Integer IdTramite;

}
