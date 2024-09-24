package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import utn.agiles.gestion_costos_backend.models.Ingrediente;

@Repository
public interface IngredienteRepository extends CrudRepository<Ingrediente, Integer> {
}
