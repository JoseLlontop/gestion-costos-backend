package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.UnidadMedidaModel;
import utn.agiles.gestion_costos_backend.repository.IRepositoryUnidadMedida;

import java.util.List;

@Service
public class MarcaServices {

    @Autowired
    private IRepositoryUnidadMedida repository;

    public List<UnidadMedidaModel> getAll() {
        return (List<UnidadMedidaModel>) repository.findAll();
    }

    public void remove(int id) {
        repository.deleteById(id);
    }

    public void save(UnidadMedidaModel producto) {
        repository.save(producto);
    }
}
