package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import java.math.BigDecimal;
import java.util.List;

public class CarritoDto {
    public Long idCarrito;
    public Long idUsuario;
    public String emailUsuario;
    public BigDecimal totalPrecio;
    public List<LineaCarritoDto> lineas;
}