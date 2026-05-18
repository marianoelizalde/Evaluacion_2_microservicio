package com.Automotriz.usuarioMS.controller;

import com.Automotriz.usuarioMS.model.Antecedentes;
import com.Automotriz.usuarioMS.service.AntecedentesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/antecedentes")
public class AntecedentesController {

    @Autowired
    private AntecedentesService service;

    @GetMapping
    public ResponseEntity<List<Antecedentes>> listar() {
        List<Antecedentes> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Antecedentes> buscar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<Antecedentes> porCliente(@PathVariable Integer clienteId) {
        try {
            return ResponseEntity.ok(service.buscarPorCliente(clienteId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Antecedentes> guardar(@RequestBody Antecedentes antecedentes) {
        return ResponseEntity.ok(service.guardar(antecedentes));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Antecedentes> actualizar(@PathVariable Integer id, @RequestBody Antecedentes antecedentes) {
        try {
            return ResponseEntity.ok(service.actualizar(id, antecedentes));
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
