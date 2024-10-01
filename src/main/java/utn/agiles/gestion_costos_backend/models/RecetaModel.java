package utn.agiles.gestion_costos_backend.models;



import org.hibernate.annotations.Array;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "receta")
public class RecetaModel {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_receta")
    private String nombreReceta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "cantidad_porciones")
    private int cantidadPorciones;

    @Column(name = "costo_total")
    private float costoTotal;

    @Column(name = "costo_porcion")
    private float costoPorcion;

    
}
