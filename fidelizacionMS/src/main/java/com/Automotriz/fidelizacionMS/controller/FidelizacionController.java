package com.Automotriz.fidelizacionMS.controller;

import com.Automotriz.fidelizacionMS.client.UsuarioClient;
import com.Automotriz.fidelizacionMS.dto.UsuarioDTO;
import com.Automotriz.fidelizacionMS.model.Fidelizacion;
import com.Automotriz.fidelizacionMS.service.FidelizacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fidelizacion")
public class FidelizacionController {

    @Autowired
    private FidelizacionService service;

    @Autowired
    private UsuarioClient usuarioClient;

    @GetMapping
    public ResponseEntity<List<Fidelizacion>> listar() {
        List<Fidelizacion> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fidelizacion> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Fidelizacion> porRut(@PathVariable String rut) {
        try { return ResponseEntity.ok(service.buscarPorRut(rut)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/nivel/{nivel}")
    public ResponseEntity<List<Fidelizacion>> porNivel(@PathVariable String nivel) {
        List<Fidelizacion> lista = service.buscarPorNivel(nivel);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    // Detalle: fidelizacion + datos del usuario
    @GetMapping("/rut/{rut}/detalle")
    public ResponseEntity<?> detalle(@PathVariable String rut) {
        try {
            Fidelizacion fidelizacion = service.buscarPorRut(rut);
            UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorRut(rut);
            return ResponseEntity.ok(java.util.Map.of("fidelizacion", fidelizacion, "usuario", usuario));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    // Agregar puntos a un cliente
    @PutMapping("/rut/{rut}/puntos/{puntos}")
    public ResponseEntity<Fidelizacion> agregarPuntos(@PathVariable String rut, @PathVariable Integer puntos) {
        try { return ResponseEntity.ok(service.agregarPuntos(rut, puntos)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @PostMapping
    public ResponseEntity<Fidelizacion> guardar(@RequestBody Fidelizacion fidelizacion) {
        return ResponseEntity.ok(service.guardar(fidelizacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
