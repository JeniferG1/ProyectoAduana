package com.proyectoaduana.msusuariosistema.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "usuario_sistema")
public class UsuarioSistema {

    @Id
    @Column(name = "id_user", nullable = false)
    private Integer idUser;

    @Column(name = "nombre_usuario", length = 50)
    private String nombreUsuario;

    @NotBlank(message = "El password es obligatorio")
    @Size(max = 256)
    @Column(name = "password_hash", nullable = false, length = 256)
    private String passwordHash;

    @Column(name = "institucion", length = 30)
    private String institucion;

    @Column(name = "cuenta_activa")
    private Integer cuentaActiva;
}