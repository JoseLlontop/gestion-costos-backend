package utn.agiles.gestion_costos_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import utn.agiles.gestion_costos_backend.models.CategoriaUnidadModel;
import utn.agiles.gestion_costos_backend.models.UnidadMedidaModel;
import utn.agiles.gestion_costos_backend.repository.IRepositoryCategoriaUnidad;
import utn.agiles.gestion_costos_backend.repository.IRepositoryUnidadMedida;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadMedidaServices {

    @Autowired
    private IRepositoryUnidadMedida unidadMedidaRepository;

    @Autowired
    private IRepositoryCategoriaUnidad categoriaUnidadRepository;

    public List<CategoriaUnidadModel> getTodasLasCategorias() {
        return (List<CategoriaUnidadModel>) categoriaUnidadRepository.findAll();
    }
}
