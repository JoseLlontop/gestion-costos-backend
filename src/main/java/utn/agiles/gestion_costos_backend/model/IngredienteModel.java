package utn.agiles.gestion_costos_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ingrediente")
public class IngredienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    @Column
    private float unidad;

    @Column
    private float precio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getUnidad() {
        return unidad;
    }

    public void setUnidad(float unidad) {
        this.unidad = unidad;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
