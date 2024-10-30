package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaId;
import utn.agiles.gestion_costos_backend.models.IngredienteXRecetaModel;
import utn.agiles.gestion_costos_backend.DTO.*;

import java.util.List;

@Repository
public interface IIngredienteXRecetaRepository extends JpaRepository<IngredienteXRecetaModel, IngredienteXRecetaId> {
    List<IngredienteXRecetaModel> findByRecetaId(Long recetaId);

    @Query("SELECT new utn.agiles.gestion_costos_backend.DTO.IngredienteXRecetaDto(i.id, ir.cantidad, ir.costo, i.nombre, i.marca) " +
        "FROM IngredienteXRecetaModel ir INNER JOIN ir.ingrediente i " +
        "WHERE ir.id.recetaId = :recetaId")
    List<IngredienteXRecetaDto> findIngredientesByRecetaId(@Param("recetaId") Long recetaId);
    List<IngredienteXRecetaModel> findByIngredienteId( Long recetaId);

}