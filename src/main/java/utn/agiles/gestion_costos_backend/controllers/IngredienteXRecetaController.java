package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteXRecetaRepository;
import utn.agiles.gestion_costos_backend.services.IngredienteXRecetaServices;
import utn.agiles.gestion_costos_backend.DTO.*;
import java.util.List;

@RestController
@RequestMapping("api/IngredientesXReceta")
public class IngredienteXRecetaController {

    @Autowired
    private IngredienteXRecetaServices ingredienteXRecetaServices;

    @Autowired
    private IIngredienteXRecetaRepository ingredienteXRecetaRepository;

    

    @PostMapping("/crear")
    public ResponseEntity<IngredienteXRecetaModel> createIngredienteXReceta(@RequestBody IngredienteXRecetaModel ingredienteXRecetaModel){
        return ResponseEntity.ok(this.ingredienteXRecetaServices.createIngredienteXReceta(ingredienteXRecetaModel));
    }

    @GetMapping("/getxRecetaId={recetaId}")
    public ResponseEntity<List<IngredienteXRecetaDto>> findIngredientesByRecetaId(@PathVariable Long recetaId) {
        List<IngredienteXRecetaDto> ingredientesXReceta = ingredienteXRecetaRepository.findIngredientesByRecetaId(recetaId);
        
        return ResponseEntity.ok(ingredientesXReceta); 
    }


    @DeleteMapping("/{recetaId}/{ingredienteId}")
    public ResponseEntity<Void> deleteIngredienteXReceta(@PathVariable Long recetaId, @PathVariable Long ingredienteId) {
        boolean deleted = ingredienteXRecetaServices.deleteIngredienteXReceta(recetaId, ingredienteId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/agregar")
    public IngredienteXRecetaModel addIngredienteToReceta(@RequestParam Long recetaId, @RequestParam Long ingredienteId, @RequestParam float cantidad) {
        return this.ingredienteXRecetaServices.addIngredienteToReceta(recetaId, ingredienteId, cantidad);
    }

    @PutMapping("/modificarCantidad")
    public ResponseEntity<IngredienteXRecetaModel> updateIngredienteXReceta(@RequestBody IngredienteXRecetaModel ingredienteXRecetaModel){
        return ResponseEntity.ok(this.ingredienteXRecetaServices.updateIngredienteXReceta(ingredienteXRecetaModel));
    }

}