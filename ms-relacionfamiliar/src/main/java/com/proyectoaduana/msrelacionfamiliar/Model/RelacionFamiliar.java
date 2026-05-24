package com.proyectoaduana.msrelacionfamiliar.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "relacion_familiar")
public class RelacionFamiliar {

    @Id
    @Column(name = "id_relacion", nullable = false)
    private Integer idRelacion;

    @Column(name = "parentesco", length = 50)
    private String parentesco;

    @Column(name = "rut_adulto", length = 20)
    private String rutAdulto;

    @Column(name = "rut_menor", length = 20)
    private String rutMenor;
}