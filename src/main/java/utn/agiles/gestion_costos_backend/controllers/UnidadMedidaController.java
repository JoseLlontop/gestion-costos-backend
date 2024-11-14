package utn.agiles.gestion_costos_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import utn.agiles.gestion_costos_backend.models.CategoriaUnidadModel;
import utn.agiles.gestion_costos_backend.models.UnidadMedidaModel;
import utn.agiles.gestion_costos_backend.services.UnidadMedidaServices;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/unidades")
public class UnidadMedidaController {

    @Autowired
    private UnidadMedidaServices unidadMedidaService;

    @GetMapping("/categorias")
    public List<CategoriaUnidadModel> getCategorias() {
        return unidadMedidaService.getTodasLasCategorias();
    }
    
}
