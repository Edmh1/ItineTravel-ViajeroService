package com.zapaticorp.viajero_service.repository;

import com.zapaticorp.viajero_service.entity.Viajero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeroRepository extends JpaRepository<Viajero, Integer> {
    // Podrías agregar consultas personalizadas, por ejemplo:
    Viajero findByEmail(String email);
}