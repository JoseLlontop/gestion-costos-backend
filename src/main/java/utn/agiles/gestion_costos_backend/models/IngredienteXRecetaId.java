package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.Embeddable;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class IngredienteXRecetaId implements Serializable {
    

    private Long recetaId;
    private Long ingredienteId;
    
    public IngredienteXRecetaId (){}
    
    public IngredienteXRecetaId(Long recetaId , Long ingredienteId){
        this.recetaId = recetaId;
        this.ingredienteId = ingredienteId;
    }

    //Getters y Setters

    public Long getRecetaId() {
        return recetaId; 
    }

    public Long getIngredienteId (){
        return ingredienteId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public void setIngredienteId(Long ingredienteId){
        this.ingredienteId = ingredienteId;
    }

    // equals y hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredienteXRecetaId that = (IngredienteXRecetaId) o;
        return Objects.equals(recetaId , that.recetaId) && Objects.equals(ingredienteId, that.ingredienteId);

    }

    @Override
    public int hashCode() {
        return Objects.hash(recetaId , ingredienteId);
    }


}