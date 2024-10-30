package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaId;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteXRecetaRepository;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;


@Service
public class IngredienteXRecetaServices {

    @Autowired
    private IIngredienteXRecetaRepository ingredienteXRecetaRepository;
    
    @Autowired
    private IRecetaRepository recetaRepository; 
    
    public IngredienteXRecetaModel createIngredienteXReceta(IngredienteXRecetaModel ingredienteXRecetaModel) {
        IngredienteXRecetaId id = new IngredienteXRecetaId(
            ingredienteXRecetaModel.getReceta().getId(),
            ingredienteXRecetaModel.getIngrediente().getId()
        );
        ingredienteXRecetaModel.setId(id);
        ingredienteXRecetaModel.calcularCosto();
        return ingredienteXRecetaRepository.save(ingredienteXRecetaModel);
    }



    public boolean deleteIngredienteXReceta(Long recetaId, Long ingredienteId) {
        IngredienteXRecetaId id = new IngredienteXRecetaId(recetaId, ingredienteId);
        if (ingredienteXRecetaRepository.existsById(id)) {
            ingredienteXRecetaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public IngredienteXRecetaModel addIngredienteToReceta(Long recetaId, Long ingredienteId, float cantidad) {
        IngredienteXRecetaId id = new IngredienteXRecetaId(recetaId, ingredienteId);
        IngredienteXRecetaModel ingredienteXReceta = new IngredienteXRecetaModel();
        ingredienteXReceta.setId(id);
        ingredienteXReceta.setCantidad(cantidad);
    
        ingredienteXReceta.calcularCosto();

        RecetaModel receta = recetaRepository.findById(recetaId).orElseThrow(() -> new RuntimeException("Receta no encontrada"));
        receta.getIngredientes().add(ingredienteXReceta); 
        ingredienteXRecetaRepository.save(ingredienteXReceta);

        receta.calcularCostoTotal(); 
        recetaRepository.save(receta);
        return ingredienteXReceta;
    }

    public IngredienteXRecetaModel updateIngredienteXReceta(IngredienteXRecetaModel ingredienteXRecetaModel) {
        IngredienteXRecetaId id = new IngredienteXRecetaId(   
        ingredienteXRecetaModel.getReceta().getId(),
        ingredienteXRecetaModel.getIngrediente().getId());

        if (ingredienteXRecetaRepository.existsById(id)) {
            ingredienteXRecetaModel.setId(id);
            ingredienteXRecetaModel.calcularCosto();
            return ingredienteXRecetaRepository.save(ingredienteXRecetaModel);
        } else {
            throw new RuntimeException("Ingrediente no encontrado");
        }
        
    }


} 