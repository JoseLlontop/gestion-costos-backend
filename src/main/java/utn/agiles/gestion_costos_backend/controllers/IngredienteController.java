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
    public IngredienteModel crearIngrediente(@RequestBody IngredienteModel ingrediente){
        return this.ingredienteServices.crearIngrediente(ingrediente);
    }

    @GetMapping("/api/ingredientes")
    public List<IngredienteModel> obtenerTodosLosIngredientes() {
        return ingredienteServices.obtenerTodosLosIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteModel> obtenerIngredientePorId(@PathVariable Long id) {
        return ingredienteServices.obtenerIngredientePorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteModel> actualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteModel detallesIngrediente) {
        return ingredienteServices.actualizarIngrediente(id, detallesIngrediente) != null
                ? ResponseEntity.ok(detallesIngrediente)
                : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable Long id) {
        ingredienteServices.eliminarIngrediente(id);
        return ResponseEntity.noContent().build();
    }

}
