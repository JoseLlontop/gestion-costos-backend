package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.IngredienteModel;
import utn.agiles.gestion_costos_backend.services.IngredienteServices;

import java.util.List;

@RestController
@RequestMapping("/api")
public class IngredienteController {

    @Autowired
    private IngredienteServices ingredienteServices;

    @PostMapping("ingredientes/crear")
    public IngredienteModel createIngrediente(@RequestBody IngredienteModel ingrediente){
        return this.ingredienteServices.createIngrediente(ingrediente);
    }

    @GetMapping("/ingredientes")
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
        return ingredienteServices.updateIngrediente(id, detallesIngrediente) != null
                ? ResponseEntity.ok(detallesIngrediente)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngrediente(@PathVariable Long id) {
        ingredienteServices.deleteIngrediente(id);
        return ResponseEntity.noContent().build();
    }

}
