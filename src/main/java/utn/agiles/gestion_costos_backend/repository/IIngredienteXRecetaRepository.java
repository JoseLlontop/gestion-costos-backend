package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaId;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;


@Repository
public interface IIngredienteXRecetaRepository extends JpaRepository<IngredienteXRecetaModel , IngredienteXRecetaId >{
    
}
