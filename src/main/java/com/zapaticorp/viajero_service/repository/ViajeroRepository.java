package com.zapaticorp.viajero_service.repository;

import com.zapaticorp.viajero_service.entity.Viajero;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajeroRepository extends JpaRepository<Viajero, Integer> {
    // Podrías agregar consultas personalizadas, por ejemplo:
    Viajero findByEmail(String email);

    @Modifying
    @Transactional
    @Query("UPDATE Viajero v SET v.activo = 0 WHERE v.idViajero = :id")
    Integer desactivarViajero(@Param("id")Integer id);
}