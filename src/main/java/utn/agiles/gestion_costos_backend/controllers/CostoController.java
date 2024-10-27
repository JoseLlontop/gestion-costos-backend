package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.CostoModel;
import utn.agiles.gestion_costos_backend.services.CostoServices;

import java.util.List;

@RestController
@RequestMapping("/api/costos")
public class CostoController {

    @Autowired
    private CostoServices costoServices;

    @PostMapping("/crear")
    public CostoModel createCosto(@RequestBody CostoModel costo){
        return this.costoServices.createCosto(costo);
    }

    @GetMapping("/")
    public List<CostoModel> getCosto() {
        return costoServices.getCostos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CostoModel> getCostoPorId(@PathVariable Long id) {
        return costoServices.getCostoPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
