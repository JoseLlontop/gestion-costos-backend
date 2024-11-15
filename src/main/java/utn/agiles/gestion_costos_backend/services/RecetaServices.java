package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import utn.agiles.gestion_costos_backend.models.CostoModel;
import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;
import utn.agiles.gestion_costos_backend.repository.ICostoRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.lang.reflect.Field;
import org.springframework.data.util.ReflectionUtils;

@Service
public class RecetaServices {
    
    @Autowired
    private IRecetaRepository recetaRepository;

    @Autowired
    private ICostoRepository costoRepository;

    public List<RecetaModel> getRecetas() {
        List<RecetaModel> recetas = recetaRepository.findAll();
        
        for (RecetaModel receta : recetas) {
            receta.calcularCostoTotal();  
        }
        
        return recetas;
    }

    public Optional<RecetaModel> getRecetaPorId(Long id) {
        return recetaRepository.findById(id);
    }

    @Transactional
    public RecetaModel createReceta(RecetaModel receta) {
        receta.calcularCostoTotal();
    
        RecetaModel recetaGuardada = recetaRepository.save(receta);
    
        List<CostoModel> costos = costoRepository.findAll();
    
        // Asignar los costos a la receta
        for (CostoModel costo : costos) {
            costo.getRecetas().add(recetaGuardada);  
    
            recetaGuardada.getCostosAdicionales().add(costo);  
        }
    
        costoRepository.saveAll(costos);
    
        return recetaGuardada;  
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

