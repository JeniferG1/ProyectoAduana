package com.proyectoaduana.msdetalleproducto.Model;


import jakarta.persistence.*; //para conectar con la base de datos
import jakarta.validation.constraints.*; // validar los datos
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bd_detalleproducto")
public class DetalleProducto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Integer iditem;

    @NotBlank(message = "La categoria es obligatoria")
    @Size(max = 50)
    @Column(name = "Categoria", nullable = false, length = 50)
    private String categoria;

    @NotNull(message = "La descripcion es obligatoria")
    @Size(max = 200)
    @Column(name = "descripcion", nullable = false,length = 200)
    private String Descripcion;

    @NotNull(message = "El id de declaracion es obligatorio")
    @Column(name = "id_declaracion", nullable = false)
    private Integer idDeclaracion;
}
