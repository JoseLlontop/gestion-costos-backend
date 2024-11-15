package utn.agiles.gestion_costos_backend.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.agiles.gestion_costos_backend.models.Produccion;
import utn.agiles.gestion_costos_backend.repository.IProduccionRepository;

@Service
public class ProduccionService {
    @Autowired
    private IProduccionRepository produccionRepository;

    public List<Produccion>  getProduccion() {
        return produccionRepository.findAll();
    }

    public Optional<Produccion>  getProduccionPorId(Long id) {
        return produccionRepository.findById(id);
    }

    public Produccion createProduccion(Produccion produccion) {
        return produccionRepository.save(produccion);
    }

    public Produccion modificarProduccion(Produccion produccion) {
        return produccionRepository.save(produccion);
    }
}
