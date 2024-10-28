package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServices {
    
    @Autowired
    private IRecetaRepository recetaRepository;

    public List<RecetaModel> getRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<RecetaModel> getRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public RecetaModel createReceta(RecetaModel receta) {
        return recetaRepository.save(receta);
    }

    public RecetaModel updateReceta(Long id, RecetaModel detallesReceta) {
        return recetaRepository.findById(id).map(receta -> {
            receta.setNombreReceta(detallesReceta.getNombreReceta());
            receta.setDescripcion(detallesReceta.getDescripcion());
            receta.setPorcionesRinde(detallesReceta.getPorcionesRinde());
            
            return recetaRepository.save(receta);
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }


    public void deleteReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    public float calcularPorcentajeGanancia (long idReceta , float precioVenta){
        Optional<RecetaModel> optionalReceta = recetaRepository.findById(idReceta);

        if (optionalReceta == null) {
            throw new IllegalArgumentException("No se encontro receta con ese Id.");
        }

        RecetaModel receta = optionalReceta.get();

        if (precioVenta < 0 ) {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo");
        }

        float porcentajeGanancia = receta.calcularPorcentajeGanancia(precioVenta);


        return porcentajeGanancia;
    }

    public float calcularPrecioVenta (long idReceta , float porcentajeGanancia) {
        Optional<RecetaModel> optionalReceta = recetaRepository.findById(idReceta);

        if (optionalReceta == null ) {
            throw new IllegalArgumentException("No se encontro receta con ese id");
        }
        
        RecetaModel receta = optionalReceta.get();

        float precioVenta = receta.calcularPrecioVenta(porcentajeGanancia);

        return precioVenta;

    }





}
