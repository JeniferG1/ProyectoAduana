package com.proyectoaduana.msrelacionfamiliar.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @NotNull(message = "El id de relación es obligatorio")
    @Column(name = "id_relacion", nullable = false)
    private Integer idRelacion;

    @Size(max = 50, message = "El parentesco no puede superar 50 caracteres")
    @Column(name = "parentesco", length = 50)
    private String parentesco;

    @Size(max = 20, message = "El RUT del adulto no puede superar 20 caracteres")
    @Column(name = "rut_adulto", length = 20)
    private String rutAdulto;

    @Size(max = 20, message = "El RUT del menor no puede superar 20 caracteres")
    @Column(name = "rut_menor", length = 20)
    private String rutMenor;
}