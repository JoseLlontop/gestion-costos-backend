package utn.agiles.gestion_costos_backend.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import utn.agiles.gestion_costos_backend.models.MarcaModel;

@Repository
public interface IMarcaRepository extends JpaRepository<MarcaModel, Long> {
    
}
