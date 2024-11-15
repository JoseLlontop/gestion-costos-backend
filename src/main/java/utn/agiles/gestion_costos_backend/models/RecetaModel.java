package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@Entity
@Table(name = "receta")
public class RecetaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre_receta", nullable = false)
    private String nombreReceta;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "porciones_rinde")
    private Float porcionesRinde;
    
    @Column(name = "porcentaje_ganancia")
    private Double porcentajeGanancia;

    @JsonIgnore
    @OneToMany(mappedBy = "receta")
    private List<IngredienteXRecetaModel> ingredientes;

    // Relación @ManyToMany con CostoModel
    @ManyToMany
    @JoinTable(
        name = "receta_costo",
        joinColumns = @JoinColumn(name = "receta_id"),
        inverseJoinColumns = @JoinColumn(name = "costo_id")
    )
    private List<CostoModel> costosAdicionales = new ArrayList<>();

    @Transient
    private float costoTotal;

    @Transient
    private float costoPorPorcion;

    public float getCostoTotal() {
        calcularCostoTotal();
        return costoTotal;
    }

    public float getCostoPorPorcion() {
        calcularCostoPorPorcion();
        return costoPorPorcion;
    }

    public void calcularCostoTotal() {
        float costoIngredientes = 0.0f;
        float costoProduccion = 0.0f;
    
        if (ingredientes != null && !ingredientes.isEmpty()) {
            ingredientes.forEach(IngredienteXRecetaModel::calcularCosto);
            costoIngredientes = ingredientes.stream()
                .map(IngredienteXRecetaModel::getCosto)
                .reduce(0.0f, Float::sum);
        }
    
        if (costosAdicionales != null && !costosAdicionales.isEmpty()) {
            costosAdicionales.forEach(CostoModel::calcularTotalCosto);
            costoProduccion = costosAdicionales.stream()
                .map(costo -> costo.getTotalCosto() != null ? costo.getTotalCosto() : 0.0f)
                .reduce(0.0f, Float::sum);
        }
    
        this.costoTotal = costoIngredientes + costoProduccion;
        calcularCostoPorPorcion();
    }

    private void calcularCostoPorPorcion() {
        if (porcionesRinde <= 0) {
            throw new IllegalArgumentException("El número de porciones debe ser mayor a cero.");
        }
        this.costoPorPorcion = this.costoTotal / this.porcionesRinde;
    }

    @Transient
    public double getPrecioVenta() {
        return calcularPrecioVentaPorPorcion();
    }

    private double calcularPrecioVentaPorPorcion() {
        calcularCostoTotal();
        if (porcentajeGanancia <= 0) {
            throw new IllegalArgumentException("El porcentaje de ganancia debe ser mayor a cero.");
        }
        float costoPorPorcion = getCostoPorPorcion();
        return costoPorPorcion * (1 + (porcentajeGanancia / 100.0));
    }
}
