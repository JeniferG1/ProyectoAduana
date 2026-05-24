package com.proyectoaduana.msdeclaracionjurada.Model;

import jakarta.persistence.*; //para conectar con la base de datos
import jakarta.validation.constraints.*; // validar los datos
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bd_decladacionjurada")
public class DeclaracionJurada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_declaracion")
    private Integer idDeclaracion;

    @NotBlank(message = "La firma digital es obligatoria")
    @Size(max = 200)
    @Column(name = "firma_digital", nullable = false, length = 200)
    private String firmaDigital;

    @NotNull(message = "La alerta de riesgo es obligatoria")
    @Column(name = "alerta_riesgo", nullable = false)
    private Integer alertaRiesgo;

    @NotNull(message = "El id de trámite es obligatorio")
    @Column(name = "id_tramite", nullable = false)
    private Integer idTramite;
}