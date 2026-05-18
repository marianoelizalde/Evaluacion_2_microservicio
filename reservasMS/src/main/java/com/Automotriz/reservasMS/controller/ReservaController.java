package com.Automotriz.reservasMS.controller;

import com.Automotriz.reservasMS.dto.ReservaDetalleDTO;
import com.Automotriz.reservasMS.model.Reserva;
import com.Automotriz.reservasMS.service.ReservaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<Reserva>> listar() {
        List<Reserva> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/cliente/{rutCliente}")
    public ResponseEntity<List<Reserva>> porCliente(@PathVariable String rutCliente) {
        List<Reserva> lista = service.buscarPorCliente(rutCliente);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Reserva>> porEstado(@PathVariable String estado) {
        List<Reserva> lista = service.buscarPorEstado(estado);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }


    @GetMapping("/{id}/detalle")
    public ResponseEntity<ReservaDetalleDTO> detalle(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.obtenerDetalle(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

   @PostMapping
    public ResponseEntity<Reserva> guardar(@Valid @RequestBody Reserva reserva) {
        return ResponseEntity.ok(service.guardar(reserva));
    }

    @PutMapping("/{id}")
   
    public ResponseEntity<Reserva> actualizar(@PathVariable Integer id, @Valid @RequestBody Reserva reserva) {
        try {
            return ResponseEntity.ok(service.actualizar(id, reserva));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
