package utn.agiles.gestion_costos_backend.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IngredienteXRecetaDto {
    private int cantidad;
    private double costo;
    private String nombre;
    private String marca;

    public IngredienteXRecetaDto(int cantidad, double costo, String nombre, String marca) {
        this.cantidad = cantidad;
        this.costo = costo;
        this.nombre = nombre;
        this.marca = marca;
    }

}