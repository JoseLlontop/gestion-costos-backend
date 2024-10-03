package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.services.IngredienteXRecetaServices;
import java.util.List;

@RestController
@RequestMapping("api/IngredienteXReceta")
public class IngredienteXRecetaController {

    @Autowired
    private IngredienteXRecetaServices ingredienteXRecetaServices;

    @PostMapping("/crear")
    public IngredienteXRecetaModel createIngredienteXReceta(@RequestBody IngredienteXRecetaModel ingredienteXRecetaModel) {
        return this.ingredienteXRecetaServices.createIngredienteXReceta(ingredienteXRecetaModel);
    }

    @GetMapping("/getxRecetaId={recetaId}")
    public List<IngredienteXRecetaModel> findByRecetaIdAllIngredientes(@PathVariable Long recetaId) {
        return ingredienteXRecetaServices.findByRecetaIdAllIngredientes(recetaId);
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
}