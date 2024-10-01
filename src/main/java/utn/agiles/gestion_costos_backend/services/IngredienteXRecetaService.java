package utn.agiles.gestion_costos_backend.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.*;
import utn.agiles.gestion_costos_backend.repository.IIngredienteXRecetaRepository;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteXRecetaService {
    @Autowired
    private IIngredienteXRecetaRepository ingredienteXRecetaRepository;

    public IngredienteXRecetaModel createIngredienteXReceta( IngredienteXRecetaModel ingredienteXReceta) {
        
        if (ingredienteXReceta.getId() == null) {
            IngredienteXRecetaId id = new IngredienteXRecetaId(
                ingredienteXReceta.getReceta().getId(),
                ingredienteXReceta.getIngrediente().getId()
            );
            ingredienteXReceta.setId(id);
        }
        
        return ingredienteXRecetaRepository.save(ingredienteXReceta);

    } //* falta manejo de errores */

    //! tengo que hacer el repository de IngredienteXRecetaId para obtener el id con las dos foraneas y luego usarlo
    
    public boolean deleteIngredienteXReceta (Long recetaId , Long IngredienteId){
        IngredienteXRecetaId id = new IngredienteXRecetaId(recetaId , ingredienteId);
        if (ingredienteXRecetaRepository.existsById(id)){
            ingredienteXRecetaRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
   
    
}
