package utn.agiles.gestion_costos_backend.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.services.RecetaServices;
import utn.agiles.gestion_costos_backend.DTO.PorcentajeGananciaDto;
import utn.agiles.gestion_costos_backend.DTO.PrecioVentaDto;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    
    @Autowired
    private RecetaServices recetaServices;

    @PostMapping("/crear")
    public RecetaModel createReceta(@RequestBody RecetaModel receta){
        return this.recetaServices.createReceta(receta);
    }

    @GetMapping("")
    public ResponseEntity<List<RecetaModel>> getRecetas() {
        List<RecetaModel> recetas = recetaServices.getRecetas();
        return recetas.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaModel> getRecetaPorId(@PathVariable Long id){
        return recetaServices.getRecetaPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaModel> updateReceta(@PathVariable Long id , @RequestBody RecetaModel detallesReceta){
        return recetaServices.updateReceta(id, detallesReceta) != null
            ? ResponseEntity.ok(detallesReceta)
            : ResponseEntity.notFound().build();

    }

    @PatchMapping("/{id}")
    public ResponseEntity<RecetaModel> updateRecetaAtribute(@PathVariable Long id, @RequestBody Map<String, Object> detallesReceta) {
        try {
            RecetaModel updatedReceta = recetaServices.updateAttributesReceta(id, detallesReceta);
            return ResponseEntity.ok(updatedReceta);
        } catch (Exception e) {
            // Registra la excepción para poder revisarla
            e.printStackTrace(); // Puedes usar un logger en lugar de esto
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<RecetaModel> deleteReceta(@PathVariable Long id){
        recetaServices.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }


}
