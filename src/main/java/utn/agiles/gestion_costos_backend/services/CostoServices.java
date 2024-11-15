package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.CostoModel;
import utn.agiles.gestion_costos_backend.models.Produccion;
import utn.agiles.gestion_costos_backend.models.RecetaModel;
import utn.agiles.gestion_costos_backend.repository.ICostoRepository;
import utn.agiles.gestion_costos_backend.repository.IProduccionRepository;
import utn.agiles.gestion_costos_backend.repository.IRecetaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CostoServices {

    @Autowired
    ICostoRepository costoRepository;

    @Autowired
    IRecetaRepository recetaRepository;

    @Autowired
    IProduccionRepository produccionRepository;

    public List<CostoModel> getCostos() {
        return costoRepository.findAll();
    }

    public Optional<CostoModel> getCostoPorId(Long id) {
        return costoRepository.findById(id);
    }

    public CostoModel createCosto(CostoModel costo) {

        Optional<Produccion> produccionOpt = produccionRepository.findById(1L);
        if (produccionOpt.isEmpty()) {
            throw new RuntimeException("Producción con ID 1 no encontrada");
        }
    
        Produccion produccion = produccionOpt.get();
    
        costo.setProduccion(produccion);
    
        CostoModel costoGuardado = costoRepository.save(costo);
    
        costoGuardado.calcularTotalCosto();
    
        List<RecetaModel> recetas = recetaRepository.findAll();
    
        for (RecetaModel receta : recetas) {
             receta.getCostosAdicionales().add(costoGuardado);
    
            receta.calcularCostoTotal();
        }
    
        recetaRepository.saveAll(recetas);
    
        return costoGuardado;
    }
    
}
