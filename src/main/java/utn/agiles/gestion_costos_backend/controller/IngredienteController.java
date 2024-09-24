package utn.agiles.gestion_costos_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.model.IngredienteModel;
import utn.agiles.gestion_costos_backend.service.IngredienteService;

@RestController
@RequestMapping("/ingrediente")
public class IngredienteController {

    @Autowired
    private IngredienteService ingredienteService;

    @PostMapping("/crear")
    public IngredienteModel crearIngrediente(@RequestBody IngredienteModel ingrediente){
        return this.ingredienteService.crearIngrediente(ingrediente);
    }


}
