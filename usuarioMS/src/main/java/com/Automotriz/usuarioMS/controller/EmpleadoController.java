package com.Automotriz.usuarioMS.controller;

import com.Automotriz.usuarioMS.dto.EmpleadoDTO;
import com.Automotriz.usuarioMS.model.Empleado;
import com.Automotriz.usuarioMS.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/empleados")
public class EmpleadoController {

    @Autowired
    private EmpleadoService service;

    @GetMapping
    public ResponseEntity<List<Empleado>> listar() {
        List<Empleado> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> buscar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Empleado> porUsuario(@PathVariable Integer usuarioId) {
        try {
            return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/sucursal/{sucursalId}")
    public ResponseEntity<List<Empleado>> porSucursal(@PathVariable Integer sucursalId) {
        List<Empleado> lista = service.buscarPorSucursal(sucursalId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<EmpleadoDTO> obtenerDTO(@PathVariable Integer id) {
        try {
            Empleado e = service.buscarPorId(id);
            EmpleadoDTO dto = new EmpleadoDTO(e.getId(), e.getUsuario().getRut(),
                    e.getUsuario().getNombre(), e.getUsuario().getCorreo(), e.getSucursalId());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Empleado> guardar(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(service.guardar(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> actualizar(@PathVariable Integer id, @RequestBody Empleado empleado) {
        try {
            return ResponseEntity.ok(service.actualizar(id, empleado));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
