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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_relacion")
    private Integer idRelacion;

    @Size(max = 50, message = "El parentesco no puede superar 50 caracteres")
    @Column(name = "parentesco", length = 50)
    private String parentesco;

    @NotBlank(message = "El RUT del adulto es obligatorio")
    @Size(max = 20, message = "El RUT del adulto no puede superar 20 caracteres")
    @Column(name = "rut_adulto", nullable = false, length = 20)
    private String rutAdulto;

    @NotBlank(message = "El RUT del menor es obligatorio")
    @Size(max = 20, message = "El RUT del menor no puede superar 20 caracteres")
    @Column(name = "rut_menor", nullable = false, length = 20)
    private String rutMenor;
}