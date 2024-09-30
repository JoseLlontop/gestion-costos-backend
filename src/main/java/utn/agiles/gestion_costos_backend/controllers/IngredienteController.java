package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.IngredienteModel;
import utn.agiles.gestion_costos_backend.services.IngredienteService;

import java.util.List;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping("/crear")
    public IngredienteModel crearIngrediente(@RequestBody IngredienteModel ingrediente){
        return this.ingredienteService.crearIngrediente(ingrediente);
    }

    @GetMapping
    public List<IngredienteModel> obtenerTodosLosIngredientes() {
        return ingredienteService.obtenerTodosLosIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteModel> obtenerIngredientePorId(@PathVariable Long id) {
        return ingredienteService.obtenerIngredientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteModel> actualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteModel detallesIngrediente) {
        return ingredienteService.actualizarIngrediente(id, detallesIngrediente) != null
                ? ResponseEntity.ok(detallesIngrediente)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable Long id) {
        ingredienteService.eliminarIngrediente(id);
        return ResponseEntity.noContent().build();
    }

}
