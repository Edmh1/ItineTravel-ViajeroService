package com.zapaticorp.viajero_service.service.impl;

import com.zapaticorp.viajero_service.entity.Viajero;
import com.zapaticorp.viajero_service.repository.ViajeroRepository;
import com.zapaticorp.viajero_service.service.ViajeroService;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.List;

@Service
public class ViajeroServiceImpl implements ViajeroService {

    private final ViajeroRepository repository;
    private final PasswordEncoder passwordEncoder;

    public ViajeroServiceImpl(ViajeroRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Viajero crearViajero(Viajero viajero) {
        viajero.setClave(passwordEncoder.encode(viajero.getClave()));
        return repository.save(viajero);
    }

    @Override
    public List<Viajero> listarViajeros() {
        return repository.findAll();
    }

    @Override
    public Viajero obtenerPorId(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Viajero actualizarViajero(Integer id, Viajero viajero) {
        Viajero existente = repository.findById(id).orElse(null);
        if (existente != null) {
            existente.setNombreUsuario(viajero.getNombreUsuario());
            existente.setApellidoUsuario(viajero.getApellidoUsuario());
            existente.setTelefono(viajero.getTelefono());
            existente.setEmail(viajero.getEmail());
            existente.setClave(viajero.getClave());
            existente.setActivo(viajero.getActivo());
            return repository.save(existente);
        }
        return null;
    }

    @Override
    public boolean eliminarViajero(Integer id) {
        int filas = repository.desactivarViajero(id);

        if(filas == 0){
            return false;
        }

        return true;
    }

    @Override
    public Viajero obtenerPorEmail(String email) {
        return repository.findByEmail(email);
    }
}
