package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.CostoModel;
import utn.agiles.gestion_costos_backend.repository.ICostoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CostoServices {

    @Autowired
    ICostoRepository costoRepository;

    public List<CostoModel> getCostos() {
        return costoRepository.findAll();
    }

    public Optional<CostoModel> getCostoPorId(Long id) {
        return costoRepository.findById(id);
    }

    public CostoModel createCosto(CostoModel costo){
        return costoRepository.save(costo);
    }

}
