package utn.agiles.gestion_costos_backend.services.interfaces;

import utn.agiles.gestion_costos_backend.models.Ingrediente;

import java.util.List;

public interface IIngredienteService {

    List<Ingrediente> getIngredientes();
}
