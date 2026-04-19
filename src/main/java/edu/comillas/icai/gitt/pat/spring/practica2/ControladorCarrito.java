package edu.comillas.icai.gitt.pat.spring.practica2;

//package edu.comillas.icai.gitt.pat.spring.practica2;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/carritos")
public class ControladorCarrito {

    private final ServicioCarrito servicio;

    public ControladorCarrito(ServicioCarrito servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public CarritoDto crear(@RequestBody CrearCarritoRequest req) {
        return servicio.crearCarrito(req);
    }

    @GetMapping("/{idCarrito}")
    public CarritoDto obtener(@PathVariable Long idCarrito) {
        return servicio.obtenerCarrito(idCarrito);
    }

    @PostMapping("/{idCarrito}/lineas")
    public CarritoDto anadirLinea(@PathVariable Long idCarrito, @RequestBody AnadirLineaRequest req) {
        return servicio.anadirLinea(idCarrito, req);
    }

    @DeleteMapping("/{idCarrito}/lineas/{idArticulo}")
    public CarritoDto borrarLinea(@PathVariable Long idCarrito, @PathVariable Long idArticulo) {
        return servicio.borrarLinea(idCarrito, idArticulo);
    }
}
