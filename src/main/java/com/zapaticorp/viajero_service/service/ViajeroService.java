package com.zapaticorp.viajero_service.service;

import com.zapaticorp.viajero_service.entity.Viajero;
import java.util.List;

public interface ViajeroService {
    Viajero crearViajero(Viajero viajero);
    List<Viajero> listarViajeros();
    Viajero obtenerPorId(Integer id);
    Viajero actualizarViajero(Integer id, Viajero viajero);
    boolean eliminarViajero(Integer id);
    Viajero obtenerPorEmail(String email);
}
