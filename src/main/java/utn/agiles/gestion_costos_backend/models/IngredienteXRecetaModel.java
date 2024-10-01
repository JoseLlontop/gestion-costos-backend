package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name= "ingredienteXreceta")
public class IngredienteXRecetaModel {

    @Column(name = "cantidad")
    private float cantidad;

    @Column(name = "costo")
    private float costo;
    


}
