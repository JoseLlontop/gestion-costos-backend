package utn.agiles.gestion_costos_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import utn.agiles.gestion_costos_backend.models.CostoModel;

@Repository
public interface ICostoRepository extends JpaRepository<CostoModel, Long>{

}
