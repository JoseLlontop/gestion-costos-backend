package utn.agiles.gestion_costos_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.agiles.gestion_costos_backend.services.MarcaServices;
import utn.agiles.gestion_costos_backend.models.MarcaModel;
import utn.agiles.gestion_costos_backend.repository.IMarcaRepository;

@RestController
@RequestMapping("/api/marcas")
public class MarcaController {

    @Autowired
    private MarcaServices marcaServices;

    @PostMapping("/crear")
    public MarcaModel createMarca(@RequestBody MarcaModel marca){
        return this.marcaServices.createMarca(marca);
    }

    @GetMapping("/")
    public List<MarcaModel> getMarcas() {
        return marcaServices.getMarcas();        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id) {
        marcaServices.deleteMarca(id);
        return ResponseEntity.noContent().build();
    }
    
}
