package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.IngredienteModel;
import utn.agiles.gestion_costos_backend.repository.IIngredienteRepository;

import java.util.List;
import java.util.Optional;

@Service
public class IngredienteServices {

    @Autowired
    IIngredienteRepository ingredienteRepository;

    public List<IngredienteModel> getIngredientes() {
        return ingredienteRepository.findAll();
    }

    public Optional<IngredienteModel> getIngredientePorId(Long id) {
        return ingredienteRepository.findById(id);
    }

    public IngredienteModel createIngrediente(IngredienteModel ingrediente){
        return ingredienteRepository.save(ingrediente);
    }

    public IngredienteModel updateIngrediente(Long id, IngredienteModel detallesIngrediente) {
        return ingredienteRepository.findById(id)
                .map(ingrediente -> {
                    ingrediente.setNombre(detallesIngrediente.getNombre());
                    ingrediente.setMarca(detallesIngrediente.getMarca());
                    ingrediente.setPrecio(detallesIngrediente.getPrecio());
                    ingrediente.setUnidad_medida(detallesIngrediente.getUnidad_medida());
                    ingrediente.setCantidad_paquete(detallesIngrediente.getCantidad_paquete());
                    return ingredienteRepository.save(ingrediente);
                }).orElseThrow(() -> new RuntimeException("Ingrediente no encontrado"));
    }

    public void deleteIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }
}
