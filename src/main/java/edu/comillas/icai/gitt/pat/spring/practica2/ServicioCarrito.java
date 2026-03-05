package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
public class ServicioCarrito {

    private final RepositorioCarrito repoCarrito;
    private final RepositorioLineaCarrito repoLinea;

    public ServicioCarrito(RepositorioCarrito repoCarrito, RepositorioLineaCarrito repoLinea) {
        this.repoCarrito = repoCarrito;
        this.repoLinea = repoLinea;
    }

    @Transactional
    public CarritoDto crearCarrito(CrearCarritoRequest req) {
        Carrito c = new Carrito();
        c.setIdUsuario(req.idUsuario);
        c.setEmailUsuario(req.emailUsuario);
        c = repoCarrito.save(c);
        return toDto(c);
    }

    @Transactional(readOnly = true)
    public CarritoDto obtenerCarrito(Long idCarrito) {
        Carrito c = repoCarrito.findById(idCarrito).orElseThrow();
        return toDto(c);
    }

    @Transactional
    public CarritoDto anadirLinea(Long idCarrito, AnadirLineaRequest req) {
        Carrito c = repoCarrito.findById(idCarrito).orElseThrow();

        LineaCarritoId id = new LineaCarritoId(idCarrito, req.idArticulo);
        LineaCarrito linea = repoLinea.findById(id).orElse(null);

        if (linea == null) {
            linea = new LineaCarrito();
            linea.setId(id);
            linea.setCarrito(c);
            c.getLineas().add(linea);
        }

        linea.setPrecioUnitario(req.precioUnitario);
        linea.setUnidades(req.unidades);

        repoCarrito.save(c); // cascada guarda lineas
        return toDto(c);
    }

    @Transactional
    public CarritoDto borrarLinea(Long idCarrito, Long idArticulo) {
        Carrito c = repoCarrito.findById(idCarrito).orElseThrow();
        LineaCarritoId id = new LineaCarritoId(idCarrito, idArticulo);

        LineaCarrito linea = repoLinea.findById(id).orElseThrow();
        c.getLineas().remove(linea); // orphanRemoval=true => se borra en BD
        repoCarrito.save(c);

        return toDto(c);
    }

    private CarritoDto toDto(Carrito c) {
        CarritoDto dto = new CarritoDto();
        dto.idCarrito = c.getIdCarrito();
        dto.idUsuario = c.getIdUsuario();
        dto.emailUsuario = c.getEmailUsuario();
        dto.totalPrecio = c.getTotalPrecio();

        dto.lineas = new ArrayList<>();
        for (LineaCarrito l : c.getLineas()) {
            LineaCarritoDto ld = new LineaCarritoDto();
            ld.idArticulo = l.getId().getIdArticulo();
            ld.precioUnitario = l.getPrecioUnitario();
            ld.unidades = l.getUnidades();
            ld.costeLinea = l.getCosteLinea();
            dto.lineas.add(ld);
        }
        return dto;
    }
}
