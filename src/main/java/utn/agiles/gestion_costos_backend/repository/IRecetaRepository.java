package utn.agiles.gestion_costos_backend.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.agiles.gestion_costos_backend.models.RecetaModel;

@Repository
public interface IRecetaRepository extends JpaRepository<RecetaModel, Long> {

}
