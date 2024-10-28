package utn.agiles.gestion_costos_backend.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class PrecioVentaDto {

    private long recetaId;
    private float porcentajeGanancia;
    
}
