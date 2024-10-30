package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
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
            receta.setPorcentajeGanancia(detallesReceta.getPorcentajeGanancia());
            
            return recetaRepository.save(receta);
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }


    //! metodo para el patch
    public RecetaModel updateAttributesReceta(Long id, Map<String, Object> updates) {
        return recetaRepository.findById(id).map(receta -> {
            updates.forEach((key, value) -> {
                Field field = ReflectionUtils.findRequiredField(RecetaModel.class, key);
                if (field != null) {
                    field.setAccessible(true);
                    if (field.getType() == Double.class && value instanceof Number) {
                        ReflectionUtils.setField(field, receta, ((Number) value).doubleValue());
                    } else {
                        ReflectionUtils.setField(field, receta, value);
                    }
                }
            });
            return recetaRepository.save(receta);
        }).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
    }
    

    public void deleteReceta(Long id) {
        recetaRepository.deleteById(id);
    }

    

}
