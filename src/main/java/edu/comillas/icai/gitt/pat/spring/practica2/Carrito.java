package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carrito")
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCarrito;

    private Long idUsuario;

    private String emailUsuario;

    @OneToMany(mappedBy = "carrito", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LineaCarrito> lineas = new ArrayList<>();

    public Long getIdCarrito() { return idCarrito; }
    public Long getIdUsuario() { return idUsuario; }
    public String getEmailUsuario() { return emailUsuario; }
    public List<LineaCarrito> getLineas() { return lineas; }

    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public void setEmailUsuario(String emailUsuario) { this.emailUsuario = emailUsuario; }

    @Transient
    public BigDecimal getTotalPrecio() {
        BigDecimal total = BigDecimal.ZERO;
        for (LineaCarrito l : lineas) total = total.add(l.getCosteLinea());
        return total;
    }
}