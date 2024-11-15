package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "ingrediente")
public class IngredienteModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "marca")
    private String marca;

    @Column(name = "precio")
    private float precio;

    @Column(name = "unidad_medida")
    private String unidad_medida;

    @Column(name = "cantidad_paquete")
    private float cantidad_paquete;

}
