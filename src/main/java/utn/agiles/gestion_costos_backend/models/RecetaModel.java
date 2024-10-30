package utn.agiles.gestion_costos_backend.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter
@Entity
@Table(name = "receta")
public class RecetaModel {

    @Id
    @Column(name = "id")
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

    @JsonIgnore // Evita la recursión
    @OneToMany(mappedBy = "receta", cascade = CascadeType.ALL)
    private List<IngredienteXRecetaModel> ingredientes;
    
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
        if (ingredientes != null && !ingredientes.isEmpty()) {
            ingredientes.forEach(IngredienteXRecetaModel::calcularCosto);
            this.costoTotal = ingredientes.stream()
                .map(IngredienteXRecetaModel::getCosto)
                .reduce(0.0f, Float::sum); 
        } else {
            this.costoTotal = 0;
        }
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
            throw new IllegalArgumentException("El porcentaje de ganancia deseado debe ser mayor a cero.");
        }
    
        float costoPorPorcion = getCostoPorPorcion();
        return costoPorPorcion * (1 + (porcentajeGanancia / 100.0));
    }
}