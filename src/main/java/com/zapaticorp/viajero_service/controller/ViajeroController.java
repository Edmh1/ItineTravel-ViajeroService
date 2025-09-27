package com.zapaticorp.viajero_service.controller;

import com.zapaticorp.viajero_service.dto.ApiResponse;
import com.zapaticorp.viajero_service.dto.LoginResponse;
import com.zapaticorp.viajero_service.dto.RegisterRequest;
import com.zapaticorp.viajero_service.dto.ViajeroResponse;
import com.zapaticorp.viajero_service.entity.Viajero;
import com.zapaticorp.viajero_service.service.ViajeroService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/viajeros")
public class ViajeroController {

    private final ViajeroService service;
    private final PasswordEncoder passwordEncoder;

    public ViajeroController(ViajeroService service, PasswordEncoder passwordEncoder) {
        this.service = service;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping
    public ResponseEntity<ApiResponse> crear(@RequestBody RegisterRequest request) {
        Viajero existe = service.obtenerPorEmail(request.getEmail());
        if(existe.getEmail() != null){
            return  ResponseEntity.badRequest().body(new ApiResponse<ViajeroResponse>(false, null, "El usuario con este email ya existe."));
        }

        Viajero viajero = new Viajero();
        viajero.setNombreUsuario(request.getNombreUsuario());
        viajero.setApellidoUsuario(request.getApellidoUsuario());
        viajero.setEmail(request.getEmail());
        viajero.setClave(passwordEncoder.encode(request.getClave()));

        Integer id = service.crearViajero(viajero).getIdViajero();

        if(id == null){
            return  ResponseEntity.badRequest().body(new ApiResponse<ViajeroResponse>(false, null, "Error al guardar el usuario."));
        }

        return ResponseEntity.ok(new ApiResponse<ViajeroResponse>(true, new ViajeroResponse(id, viajero.getEmail()),null));
    }

    @PostMapping("/auth")
    public ResponseEntity<ApiResponse> validarViajero(@RequestBody LoginResponse request) {
        Viajero viajero = service.obtenerPorEmail(request.getEmail());

        if(viajero == null){
            return ResponseEntity.badRequest().body(new ApiResponse<ViajeroResponse>(false, null, "El usuario no existe"));
        }

        if(!passwordEncoder.matches(request.getClave(), viajero.getClave())){
            return ResponseEntity.badRequest().body(new ApiResponse<ViajeroResponse>(false, null, "Las credenciales del usuario son incorrectas"));
        }

        return ResponseEntity.ok(new ApiResponse<>(true, new ViajeroResponse(viajero.getIdViajero(), viajero.getEmail()),null));
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
    public ResponseEntity<ApiResponse> eliminar(@PathVariable Integer id) {
        boolean desactivado = service.eliminarViajero(id);

        if(!desactivado){
            return ResponseEntity.badRequest().body(new ApiResponse<ViajeroResponse>(false, null, "El usuario no existe"));
        }

        return ResponseEntity.ok(new ApiResponse<ViajeroResponse>(true, new ViajeroResponse(id, ""),null));
    }
}
