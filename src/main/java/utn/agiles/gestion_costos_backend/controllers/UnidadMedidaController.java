package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.UnidadMedidaModel;
import utn.agiles.gestion_costos_backend.services.UnidadMedidaServices;

import java.util.List;

@RestController
public class MarcaController {

    @Autowired
    private UnidadMedidaServices service;

    @GetMapping("/api/marcas")
    public List<UnidadMedidaModel> getAll(){
        return service.getAll();
    }

    @DeleteMapping("/api/marcas/{id}")
    public void remove(@PathVariable int id) {
        service.remove(id);
    }

    @PostMapping("/api/marcas")
    public void save(@RequestBody UnidadMedidaModel marca){
        service.save(marca);
    }
}
