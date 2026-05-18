package com.Automotriz.pagosMS.controller;

import com.Automotriz.pagosMS.client.ReservaClient;
import com.Automotriz.pagosMS.dto.ReservaDTO;
import com.Automotriz.pagosMS.model.Pago;
import com.Automotriz.pagosMS.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @Autowired
    private ReservaClient reservaClient;

    @GetMapping
    public ResponseEntity<List<Pago>> listar() {
        List<Pago> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pago> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<Pago>> porReserva(@PathVariable Integer reservaId) {
        List<Pago> lista = service.buscarPorReserva(reservaId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/estado/{estadoPago}")
    public ResponseEntity<List<Pago>> porEstado(@PathVariable String estadoPago) {
        List<Pago> lista = service.buscarPorEstado(estadoPago);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    // Detalle: pago + datos de la reserva
    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> detalle(@PathVariable Integer id) {
        try {
            Pago pago = service.buscarPorId(id);
            ReservaDTO reserva = reservaClient.obtenerReserva(pago.getReservaId());
            return ResponseEntity.ok(java.util.Map.of("pago", pago, "reserva", reserva));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Pago> guardar(@RequestBody Pago pago) {
        return ResponseEntity.ok(service.guardar(pago));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> actualizar(@PathVariable Integer id, @RequestBody Pago pago) {
        try { return ResponseEntity.ok(service.actualizar(id, pago)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
