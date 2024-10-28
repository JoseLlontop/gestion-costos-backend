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
    
    @Column (name = "porcentaje_ganancia")
    private float porcentajeGanancia;

    @Column (name = "precioVenta")
    private float precioVenta;

    @JsonIgnore // Esto evita la recursión
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
            // Calcula el costo para cada ingrediente en la lista
            ingredientes.forEach(IngredienteXRecetaModel::calcularCosto);
    
            // Suma todos los costos para obtener el costo total
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
    
    public float calcularPorcentajeGanancia (float precioVenta) {
        this.precioVenta = precioVenta;
        calcularCostoPorPorcion();
        this.porcentajeGanancia =  ((precioVenta*100)/this.costoPorPorcion)-100 ; 

        return this.porcentajeGanancia;
    }

    public float calcularPrecioVenta (float porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
        calcularCostoPorPorcion();
        this.precioVenta = ((100 + porcentajeGanancia) * costoPorPorcion ) / 100;

        return this.precioVenta;

    }

}



