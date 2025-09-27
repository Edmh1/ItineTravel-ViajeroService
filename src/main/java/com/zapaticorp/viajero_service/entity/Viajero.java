package com.zapaticorp.viajero_service.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "viajeros")
public class Viajero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_viajero")
    private Integer idViajero;

    @Column(name = "nombre_usuario", nullable = false)
    private String nombreUsuario;

    @Column(name = "apellido_usuario", nullable = false)
    private String apellidoUsuario;

    @Column(name = "telefono")
    private Integer telefono;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "clave", nullable = false, unique = true)
    private String clave; // Aquí luego guardaremos la contraseña encriptada

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDate fechaCreacion;

    @Column(name = "activo", nullable = false)
    private Integer activo = 1;

    public Integer getIdViajero() {
        return idViajero;
    }

    public void setIdViajero(Integer idViajero) {
        this.idViajero = idViajero;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getActivo() {
        return activo;
    }

    public void setActivo(Integer activo) {
        this.activo = activo;
    }

    // Getters y setters
    // Constructor vacío y constructor con parámetros (si deseas)
}
