package com.proyectoaduana.mspasofronterizo.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "paso_fronterizo")
public class PasoFronterizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_paso")
    private Integer idPaso;

    @NotBlank(message = "El nombre del paso es obligatorio")
    @Size(max = 100)
    @Column(name = "nombre_paso", nullable = false, length = 100)
    private String nombrePaso;

    @NotNull(message = "El codigo de pais es obligatorio")
    @Column(name = "cod_pais", nullable = false)
    private Integer codPais;
}
