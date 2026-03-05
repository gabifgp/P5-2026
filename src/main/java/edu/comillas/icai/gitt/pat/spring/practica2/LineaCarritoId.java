package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class LineaCarritoId implements Serializable {

    private Long idCarrito;
    private Long idArticulo;

    public LineaCarritoId() {}

    public LineaCarritoId(Long idCarrito, Long idArticulo) {
        this.idCarrito = idCarrito;
        this.idArticulo = idArticulo;
    }

    public Long getIdCarrito() { return idCarrito; }
    public Long getIdArticulo() { return idArticulo; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LineaCarritoId)) return false;
        LineaCarritoId that = (LineaCarritoId) o;
        return Objects.equals(idCarrito, that.idCarrito) && Objects.equals(idArticulo, that.idArticulo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrito, idArticulo);
    }
}