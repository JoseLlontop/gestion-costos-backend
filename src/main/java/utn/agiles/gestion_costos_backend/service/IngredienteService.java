package utn.agiles.gestion_costos_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.model.IngredienteModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteService {

    @Autowired
    IIngredienteRepository ingredienteRepository;

    public List<IngredienteModel> obtenerTodosLosIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Optional<IngredienteModel> obtenerIngredientePorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    public IngredienteModel crearIngrediente(IngredienteModel ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    public IngredienteModel actualizarIngrediente(Long id, IngredienteModel detallesIngrediente) {
        return ingredienteRepository.findById(id)
                .map(ingrediente -> {
                    ingrediente.setNombre(detallesIngrediente.getNombre());
                    ingrediente.setUnidad(detallesIngrediente.getUnidad());
                    ingrediente.setPrecio(detallesIngrediente.getPrecio());
                    return ingredienteRepository.save(ingrediente);
                }).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
    }

    public void eliminarIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
