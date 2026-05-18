package com.Automotriz.soporteMS.controller;

import com.Automotriz.soporteMS.client.ReservaClient;
import com.Automotriz.soporteMS.dto.ReservaDTO;
import com.Automotriz.soporteMS.model.Ticket;
import com.Automotriz.soporteMS.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    @Autowired
    private TicketService service;

    @Autowired
    private ReservaClient reservaClient;

    @GetMapping
    public ResponseEntity<List<Ticket>> listar() {
        List<Ticket> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<Ticket>> porReserva(@PathVariable Integer reservaId) {
        List<Ticket> lista = service.buscarPorReserva(reservaId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Ticket>> porEstado(@PathVariable String estado) {
        List<Ticket> lista = service.buscarPorEstado(estado);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        try {
            Ticket ticket = service.buscarPorId(id);
            ReservaDTO reserva = reservaClient.obtenerReserva(ticket.getReservaId());
            return ResponseEntity.ok(java.util.Map.of("ticket", ticket, "reserva", reserva));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Ticket> guardar(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(service.guardar(ticket));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> actualizar(@PathVariable Integer id, @RequestBody Ticket ticket) {
        try { return ResponseEntity.ok(service.actualizar(id, ticket)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
