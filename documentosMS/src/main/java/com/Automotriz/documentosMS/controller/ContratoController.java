package com.Automotriz.documentosMS.controller;

import com.Automotriz.documentosMS.client.ReservaClient;
import com.Automotriz.documentosMS.dto.ReservaDTO;
import com.Automotriz.documentosMS.model.Contrato;
import com.Automotriz.documentosMS.service.ContratoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/contratos")
public class ContratoController {

    @Autowired
    private ContratoService service;

    @Autowired
    private ReservaClient reservaClient;

    @GetMapping
    public ResponseEntity<List<Contrato>> listar() {
        List<Contrato> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrato> buscar(@PathVariable @NonNull Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/reserva/{reservaId}")
    public ResponseEntity<List<Contrato>> porReserva(@PathVariable Integer reservaId) {
        List<Contrato> lista = service.buscarPorReserva(reservaId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    // Detalle: contrato + datos de la reserva
    @GetMapping("/{id}/detalle")
    public ResponseEntity<?> detalle(@PathVariable @NonNull Integer id) {
        try {
            Contrato contrato = service.buscarPorId(id);
            ReservaDTO reserva = reservaClient.obtenerReserva(contrato.getReservaId());
            return ResponseEntity.ok(java.util.Map.of("contrato", contrato, "reserva", reserva));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Contrato> guardar(@RequestBody Contrato contrato) {
        return ResponseEntity.ok(service.guardar(contrato));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrato> actualizar(@PathVariable @NonNull Integer id, @RequestBody Contrato contrato) {
        try { return ResponseEntity.ok(service.actualizar(id, contrato)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable @NonNull Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
