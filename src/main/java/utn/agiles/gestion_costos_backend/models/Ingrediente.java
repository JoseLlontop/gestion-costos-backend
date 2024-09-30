package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "ingrediente")
public class Ingrediente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "costo_unidad")
    private float costo_unidad;

    @Column(name = "unidad_medida")
    private String unidad_medida;

    @Column(name = "stock")
    private int stock;

}


