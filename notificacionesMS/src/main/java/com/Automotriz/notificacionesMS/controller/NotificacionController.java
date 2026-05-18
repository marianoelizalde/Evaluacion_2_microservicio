package com.Automotriz.notificacionesMS.controller;

import com.Automotriz.notificacionesMS.client.ReservaClient;
import com.Automotriz.notificacionesMS.dto.ReservaDTO;
import com.Automotriz.notificacionesMS.model.Notificacion;
import com.Automotriz.notificacionesMS.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService service;

    @Autowired
    private ReservaClient reservaClient;

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() {
        List<Notificacion> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<Notificacion>> porReserva(@PathVariable Integer reservaId) {
        List<Notificacion> lista = service.buscarPorReserva(reservaId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Notificacion>> porEstado(@PathVariable String estado) {
        List<Notificacion> lista = service.buscarPorEstado(estado);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        try {
            Notificacion notificacion = service.buscarPorId(id);
            ReservaDTO reserva = reservaClient.obtenerReserva(notificacion.getReservaId());
            return ResponseEntity.ok(java.util.Map.of("notificacion", notificacion, "reserva", reserva));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Notificacion> guardar(@RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(service.guardar(notificacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(@PathVariable Integer id, @RequestBody Notificacion notificacion) {
        try { return ResponseEntity.ok(service.actualizar(id, notificacion)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
