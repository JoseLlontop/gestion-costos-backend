package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaId;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;

import java.util.List;

@Repository
public interface IIngredienteXRecetaRepository extends JpaRepository<IngredienteXRecetaModel, IngredienteXRecetaId> {
    List<IngredienteXRecetaModel> findByRecetaId(Long recetaId);

    @Query("SELECT ir FROM ingredienteXreceta ir JOIN FETCH ir.ingrediente WHERE ir.receta.id = :recetaId")
    List<IngredienteXRecetaModel> findByRecetaIdAllIngredientes(Long recetaId);

}