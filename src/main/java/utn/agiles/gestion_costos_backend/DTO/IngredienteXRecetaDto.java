package utn.agiles.gestion_costos_backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IngredienteXRecetaDto {
    private Long id;
    private float cantidad;
    private float costo;
    private String nombre;
    private String marca;

    public IngredienteXRecetaDto(Long id,float cantidad, float costo, String nombre, String marca) {
        this.id = id;
        this.cantidad = cantidad;
        this.costo = costo;
        this.nombre = nombre;
        this.marca = marca;
    }

}