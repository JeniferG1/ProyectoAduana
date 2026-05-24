package com.proyectoaduana.mstramitefronterizo.Model;

import jakarta.persistence.*;
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
    @Column(name = "id_tramite", nullable = false)
    private Integer idTramite;

    @Column(name = "fecha_hora", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Column(name = "sentido", length = 10)
    private String sentido;

    @Column(name = "id_paso")
    private Integer idPaso;

    @Column(name = "rut_pasajero", length = 20)
    private String rutPasajero;

    @Column(name = "id_funcionario")
    private Integer idFuncionario;
}
