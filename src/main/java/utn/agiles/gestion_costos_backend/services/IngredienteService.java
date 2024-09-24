package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.Ingrediente;
import utn.agiles.gestion_costos_backend.repository.IngredienteRepository;
import utn.agiles.gestion_costos_backend.services.interfaces.IIngredienteService;

import java.util.List;

@Service
public class IngredienteService implements IIngredienteService {

    @Autowired
    private IngredienteRepository repository;

    @Override
    public List<Ingrediente> getIngredientes() {
        return (List<Ingrediente>) repository.findAll();
    }
}
