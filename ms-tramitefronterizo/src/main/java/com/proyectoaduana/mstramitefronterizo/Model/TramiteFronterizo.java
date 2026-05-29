package com.proyectoaduana.mstramitefronterizo.Model;

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
@Table(name = "tramite_fronterizo")
public class TramiteFronterizo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tramite")
    private Integer idTramite;

    @NotNull(message = "La fecha y hora es obligatoria")
    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Size(max = 10, message = "El sentido no puede superar 10 caracteres")
    @Column(name = "sentido", length = 10)
    private String sentido;

    @NotNull(message = "El id del paso fronterizo es obligatorio")
    @Column(name = "id_paso", nullable = false)
    private Integer idPaso;

    @NotBlank(message = "El RUT del pasajero es obligatorio")
    @Size(max = 20, message = "El RUT del pasajero no puede superar 20 caracteres")
    @Column(name = "rut_pasajero", nullable = false, length = 20)
    private String rutPasajero;

    @NotNull(message = "El id del funcionario es obligatorio")
    @Column(name = "id_funcionario", nullable = false)
    private Integer idFuncionario;
}