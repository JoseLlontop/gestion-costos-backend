package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.*;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaServices {
    @Autowired
    IRecetaRepository recetaRepository;

    public List<RecetaModel> getRecetas() {
        return recetaRepository.findAll();
    }

    public Optional<RecetaModel> getRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    public RecetaModel createReceta(RecetaModel receta){
        return recetaRepository.save(receta);
    }

    public RecetaModel updateReceta(Long id, RecetaModel detallesReceta){
        return recetaRepository.findById(id).map(receta -> {
            receta.setNombreReceta(detallesReceta.getNombreReceta());
            receta.setDescripcion(detallesReceta.getDescripcion());
            receta.setCantidadPorciones(detallesReceta.getCantidadPorciones());
            receta.setCostoTotal(detallesReceta.getCostoTotal());
            receta.setCostoPorcion(detallesReceta.getCostoPorcion());
            return recetaRepository.save(receta);
        
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }




    public void deleteReceta(Long id) {
        recetaRepository.deleteById(id);
    }
    
}
