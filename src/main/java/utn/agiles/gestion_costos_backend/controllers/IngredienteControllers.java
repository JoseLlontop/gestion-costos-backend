package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import utn.agiles.gestion_costos_backend.models.Ingrediente;
import utn.agiles.gestion_costos_backend.services.interfaces.IIngredienteService;

import java.util.List;

@RestController
public class IngredienteControllers {

    @Autowired
    private IIngredienteService service;

    @GetMapping("/api/ingredientes")
    public List<Ingrediente> getIngredientes() {
        return service.getIngredientes();
    }
}
