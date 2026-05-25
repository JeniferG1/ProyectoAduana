package com.proyectoaduana.mspais.Model;
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
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cod_pais", nullable = false)
    private Integer codPais;

    @NotBlank(message = "El nombre del país es obligatorio")
    @Size(max = 50)
    @Column(name = "nombre_pais", nullable = false, length = 50)
    private String nombrePais;
}
