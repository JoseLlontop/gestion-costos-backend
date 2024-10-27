package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.IngredienteModel;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteXRecetaRepository;
import utn.agiles.gestion_costos_backend.services.IngredienteServices;

import java.util.List;

@RestController
@RequestMapping("/api/ingredientes")
public class IngredienteController {

    @Autowired
    private IngredienteServices ingredienteServices;
    @Autowired
    private IIngredienteXRecetaRepository ingredienteXRecetaRepository;


    @PostMapping("/crear")
    public IngredienteModel createIngrediente(@RequestBody IngredienteModel ingrediente){
        return this.ingredienteServices.createIngrediente(ingrediente);
    }

    @GetMapping("/")
    public List<IngredienteModel> getIngredientes() {
        return ingredienteServices.getIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteModel> getIngredientePorId(@PathVariable Long id) {
        return ingredienteServices.getIngredientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteModel> updateIngrediente(@PathVariable Long id, @RequestBody IngredienteModel detallesIngrediente) {
        // Actualizar el ingrediente
        IngredienteModel ingredienteActualizado = ingredienteServices.updateIngrediente(id, detallesIngrediente);
        
        if (ingredienteActualizado == null) {
            return ResponseEntity.notFound().build();
        }

        // Actualizar el costo en IngredienteXReceta
        List<IngredienteXRecetaModel> ingredientesXReceta = ingredienteXRecetaRepository.findByIngredienteId(id);
        
        for (IngredienteXRecetaModel ingredienteXReceta : ingredientesXReceta) {
            // Recalcular el costo
            ingredienteXReceta.calcularCosto(); 
            ingredienteXRecetaRepository.save(ingredienteXReceta); 
        }

        return ResponseEntity.ok(ingredienteActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        ingredienteServices.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }

}
