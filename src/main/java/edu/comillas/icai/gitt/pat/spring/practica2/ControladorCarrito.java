package edu.comillas.icai.gitt.pat.spring.practica2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/carritos")
public class ControladorCarrito {

    private final Map<Long, ModeloCarrito> datos = new HashMap<>();
    private final AtomicLong generadorId = new AtomicLong(1);

    @GetMapping
    public List<ModeloCarrito> getAll() {
        return new ArrayList<>(datos.values());
    }


    @GetMapping("/{idCarrito}")
    public ResponseEntity<ModeloCarrito> getById(@PathVariable Long idCarrito) {
        ModeloCarrito carrito = datos.get(idCarrito);
        if (carrito == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(carrito);
    }

    // crear
    @PostMapping
    public ResponseEntity<ModeloCarrito> create(@RequestBody ModeloCarrito carrito) {
        Long nuevoId = generadorId.getAndIncrement();
        carrito.setIdCarrito(nuevoId);

        if (carrito.getPrecioFinal() == null) {
            carrito.setPrecioFinal(calcularPrecioFinal(carrito));
        }

        datos.put(nuevoId, carrito);
        return ResponseEntity.status(HttpStatus.CREATED).body(carrito);
    }

    // actualizar
    @PutMapping("/{idCarrito}")
    public ResponseEntity<ModeloCarrito> update(@PathVariable Long idCarrito,
                                                @RequestBody ModeloCarrito carrito) {
        if (!datos.containsKey(idCarrito)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        carrito.setIdCarrito(idCarrito);

        if (carrito.getPrecioFinal() == null) {
            carrito.setPrecioFinal(calcularPrecioFinal(carrito));
        }

        datos.put(idCarrito, carrito);
        return ResponseEntity.ok(carrito);
    }

    // borrar
    @DeleteMapping("/{idCarrito}")
    public ResponseEntity<Void> delete(@PathVariable Long idCarrito) {
        if (!datos.containsKey(idCarrito)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        datos.remove(idCarrito);
        return ResponseEntity.noContent().build();
    }

    private double calcularPrecioFinal(ModeloCarrito carrito) {
        int u = (carrito.getUnidades() == null) ? 0 : carrito.getUnidades();
        return u * 10.0;
    }


}


