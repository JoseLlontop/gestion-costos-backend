package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.repository.CrudRepository;
import utn.agiles.gestion_costos_backend.models.UnidadMedidaModel;

public interface IRepositoryMarca extends CrudRepository<UnidadMedidaModel, Integer> {
}
