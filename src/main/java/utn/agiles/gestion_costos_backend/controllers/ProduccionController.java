package utn.agiles.gestion_costos_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import utn.agiles.gestion_costos_backend.models.Produccion;
import utn.agiles.gestion_costos_backend.services.ProduccionService;

@RestController
@RequestMapping("/api/produccion")
public class ProduccionController {
    
    @Autowired
    private ProduccionService produccionServices;


    @GetMapping("/")
    public List<Produccion> getProduccion() {
        return produccionServices.getProduccion();
    }

    
    @GetMapping("/{id}") 
    public ResponseEntity<Produccion> getProduccionPorId(@PathVariable Long id) {
        return produccionServices.getProduccionPorId(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/crear")
    public Produccion createProduccion(@RequestBody Produccion produccion) {
        return this.produccionServices.createProduccion(produccion);
    }

    @PutMapping("/modificar/{id}")
    public Produccion modificarProduccion(@PathVariable Long id, @RequestBody Produccion produccion) {
        produccion.setId(id);
        return this.produccionServices.modificarProduccion(produccion);
    }
}
