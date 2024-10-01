package utn.agiles.gestion_costos_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.services.RecetaServices;

@RestController
@RequestMapping("/api/recetas")
public class RecetaController {
    
    @Autowired
    private RecetaServices recetaServices;

    @PostMapping("/crear")
    public RecetaModel createReceta(@RequestBody RecetaModel receta){
        return this.recetaServices.createReceta(receta);
    }

    @GetMapping
    public List<RecetaModel> getRecetas() {
        return recetaServices.getRecetas();
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

    @DeleteMapping("/{id}")
    public ResponseEntity<RecetaModel> deleteReceta(@PathVariable Long id){
        recetaServices.deleteReceta(id);
        return ResponseEntity.noContent().build();
    }
}
