package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name= "ingredienteXreceta")
public class IngredienteXRecetaModel {

    @EmbeddedId
    private IngredienteXRecetaId id;

    @ManyToOne
    @MapsId("recetaId")
    @JoinColumn(name = "receta_id")
    private RecetaModel receta;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingrediente_id")
    private IngredienteModel ingrediente;

    @Column(name = "cantidad")
    private float cantidad;

    @Column(name = "costo")
    private float costo;
    

}