package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "linea_carrito")
public class LineaCarrito {

    @EmbeddedId
    private LineaCarritoId id;

    @ManyToOne(optional = false)
    @MapsId("idCarrito")
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    private BigDecimal precioUnitario;
    private Integer unidades;

    public LineaCarritoId getId() { return id; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public Integer getUnidades() { return unidades; }

    public void setId(LineaCarritoId id) { this.id = id; }
    public void setCarrito(Carrito carrito) { this.carrito = carrito; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }
    public void setUnidades(Integer unidades) { this.unidades = unidades; }

    @Transient
    public BigDecimal getCosteLinea() {
        if (precioUnitario == null || unidades == null) return BigDecimal.ZERO;
        return precioUnitario.multiply(BigDecimal.valueOf(unidades));
    }
}
