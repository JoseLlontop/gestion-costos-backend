package utn.agiles.gestion_costos_backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import utn.agiles.gestion_costos_backend.models.MarcaModel;
import utn.agiles.gestion_costos_backend.repository.IMarcaRepository;

@Service
public class MarcaServices {
    
    
    @Autowired
    IMarcaRepository marcaRepository;

    public List<MarcaModel> getMarcas() {
        return marcaRepository.findAll();
    }

    public MarcaModel createMarca(MarcaModel marca){
        return marcaRepository.save(marca);
    }

    public void deleteMarca(Long id){
        marcaRepository.deleteById(id);
    }

}
