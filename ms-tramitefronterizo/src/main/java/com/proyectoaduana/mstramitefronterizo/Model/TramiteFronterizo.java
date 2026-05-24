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
    @NotNull(message = "El id del tramite es obligatorio")
    @Column(name = "id_tramite", nullable = false)
    private Integer idTramite;

    @NotNull(message = "La fecha y hora es obligatoria")
    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Size(max = 10, message = "El sentido no puede superar 10 caracteres")
    @Column(name = "sentido", length = 10)
    private String sentido;

    @Column(name = "id_paso")
    private Integer idPaso;

    @Size(max = 20, message = "El RUT del pasajero no puede superar 20 caracteres")
    @Column(name = "rut_pasajero", length = 20)
    private String rutPasajero;

    @Column(name = "id_funcionario")
    private Integer idFuncionario;
}