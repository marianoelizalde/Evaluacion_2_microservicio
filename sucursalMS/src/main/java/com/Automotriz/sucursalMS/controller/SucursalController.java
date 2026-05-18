package com.Automotriz.sucursalMS.controller;

import com.Automotriz.sucursalMS.dto.SucursalDTO;
import com.Automotriz.sucursalMS.model.Sucursal;
import com.Automotriz.sucursalMS.service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/sucursales")
public class SucursalController {

    @Autowired
    private SucursalService service;

    @GetMapping
    public ResponseEntity<List<Sucursal>> listar() {
        List<Sucursal> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sucursal> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/comuna/{comuna}")
    public ResponseEntity<List<Sucursal>> porComuna(@PathVariable String comuna) {
        List<Sucursal> lista = service.buscarPorComuna(comuna);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<SucursalDTO> obtenerDTO(@PathVariable Integer id) {
        try {
            Sucursal s = service.buscarPorId(id);
            SucursalDTO dto = new SucursalDTO(s.getId(), s.getNombre(), s.getDireccion(), s.getComuna());
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Sucursal> guardar(@RequestBody Sucursal sucursal) {
        return ResponseEntity.ok(service.guardar(sucursal));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sucursal> actualizar(@PathVariable Integer id, @RequestBody Sucursal sucursal) {
        try { return ResponseEntity.ok(service.actualizar(id, sucursal)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
