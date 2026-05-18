package com.Automotriz.vehiculoMS.controller;

import com.Automotriz.vehiculoMS.dto.VehiculoDTO;
import com.Automotriz.vehiculoMS.model.Vehiculo;
import com.Automotriz.vehiculoMS.service.VehiculoService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/vehiculos")
public class VehiculoController {

    @Autowired
    private VehiculoService service;

    @GetMapping
    public ResponseEntity<List<Vehiculo>> listar() {
        List<Vehiculo> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<Vehiculo>> listarDisponibles() {
        List<Vehiculo> lista = service.listarDisponibles();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/patente/{patente}")
    public ResponseEntity<Vehiculo> buscarPorPatente(@PathVariable String patente) {
        try { return ResponseEntity.ok(service.buscarPorPatente(patente)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<VehiculoDTO> obtenerDTO(@PathVariable Integer id) {
        try {
            Vehiculo v = service.buscarPorId(id);
            VehiculoDTO dto = new VehiculoDTO(v.getId(), v.getNombre(), v.getModelo(),
                    v.getMarca(), v.getPatente(), v.getEstado());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

   @PostMapping
    public ResponseEntity<Vehiculo> guardar(@Valid @RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(service.guardar(vehiculo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehiculo> actualizar(@PathVariable Integer id, @Valid @RequestBody Vehiculo vehiculo) {
        try {
            return ResponseEntity.ok(service.actualizar(id, vehiculo));
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
