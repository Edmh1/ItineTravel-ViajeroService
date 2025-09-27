package com.zapaticorp.viajero_service.controller;

import com.zapaticorp.viajero_service.entity.Viajero;
import com.zapaticorp.viajero_service.service.ViajeroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajeros")
public class ViajeroController {

    private final ViajeroService service;

    public ViajeroController(ViajeroService service) {
        this.service = service;
    }

    @PostMapping
    public Viajero crear(@RequestBody Viajero viajero) {
        return service.crearViajero(viajero);
    }

    @GetMapping
    public List<Viajero> listar() {
        return service.listarViajeros();
    }

    @GetMapping("/{id}")
    public Viajero obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PutMapping("/{id}")
    public Viajero actualizar(@PathVariable Integer id, @RequestBody Viajero viajero) {
        return service.actualizarViajero(id, viajero);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminarViajero(id);
    }
}
