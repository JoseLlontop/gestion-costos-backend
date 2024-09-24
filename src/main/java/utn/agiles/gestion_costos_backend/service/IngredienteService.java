package utn.agiles.gestion_costos_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.model.IngredienteModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteRepository;

@Service
public class IngredienteService {

    @Autowired
    IIngredienteRepository ingredienteRepository;

    public IngredienteModel crearIngrediente(IngredienteModel ingrediente){
        return ingredienteRepository.save(ingrediente);
    }
}
