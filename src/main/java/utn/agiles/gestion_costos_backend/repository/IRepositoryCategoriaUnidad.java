package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.repository.CrudRepository;
import utn.agiles.gestion_costos_backend.models.CategoriaUnidadModel;

public interface IRepositoryCategoriaUnidad extends CrudRepository<CategoriaUnidadModel, Long> {
}
