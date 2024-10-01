package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.services.IngredienteXRecetaServices;

@RestController
@RequestMapping("api/IngredienteXReceta")
public class IngredienteXRecetaController {

    @Autowired
    private IngredienteXRecetaServices ingredienteXRecetaServices;

    @PostMapping("/crear")
    public IngredienteXRecetaModel createIngredienteXReceta(@RequestBody IngredienteXRecetaModel ingredienteXReceta) {
        return this.ingredienteXRecetaServices.createIngredienteXReceta(ingredienteXReceta);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredienteXReceta(@PathVariable Long idReceta , Long idIngrediente){
        ingredienteXRecetaServices.deleteIngredienteXReceta(idReceta, idIngrediente);
        return ResponseEntity.noContent().build();
    }
    
}
