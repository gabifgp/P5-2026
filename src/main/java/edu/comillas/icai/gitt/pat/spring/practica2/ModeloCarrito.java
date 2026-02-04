package edu.comillas.icai.gitt.pat.spring.practica2;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModeloCarrito {
    private Long idCarrito;
    private Long idArticulo;
    private String descripcion;
    private Integer unidades;
    private Double precioFinal;
}
