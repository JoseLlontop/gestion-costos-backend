package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Setter
@Getter
@Entity
@Table(name = "costo")
public class CostoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo; // Ej. "fijo", "variable", etc.

    @Column(name = "valor")
    private float valor;

    @ManyToOne
    @JoinColumn(name = "produccion_id")
    private Produccion produccion;

    // Relación @ManyToMany con RecetaModel
    @ManyToMany(mappedBy = "costosAdicionales")
    @JsonBackReference
    private List<RecetaModel> recetas = new ArrayList<>();

    @Transient
    private Float totalCosto;

    public Float getTotalCosto() {
        if (totalCosto == null) {
            calcularTotalCosto();
        }
        return totalCosto != null ? totalCosto : 0.0f;
    }

    public void calcularTotalCosto() {
        if (produccion != null && produccion.getCantidad_producida_mensualmente() != null && produccion.getCantidad_producida_mensualmente() > 0) {
            totalCosto = valor / produccion.getCantidad_producida_mensualmente();
        } else {
            totalCosto = valor;
        }
    }
}
