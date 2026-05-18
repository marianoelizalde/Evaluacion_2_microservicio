package com.Automotriz.vehiculoMS.controller;

import com.Automotriz.vehiculoMS.model.Mantencion;
import com.Automotriz.vehiculoMS.service.MantencionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/mantenciones")
public class MantencionController {

    @Autowired
    private MantencionService service;

    @GetMapping
    public ResponseEntity<List<Mantencion>> listar() {
        List<Mantencion> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mantencion> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/vehiculo/{vehiculoId}")
    public ResponseEntity<List<Mantencion>> porVehiculo(@PathVariable Integer vehiculoId) {
        List<Mantencion> lista = service.buscarPorVehiculo(vehiculoId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Mantencion> guardar(@RequestBody Mantencion mantencion) {
        return ResponseEntity.ok(service.guardar(mantencion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mantencion> actualizar(@PathVariable Integer id, @RequestBody Mantencion mantencion) {
        try { return ResponseEntity.ok(service.actualizar(id, mantencion)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
