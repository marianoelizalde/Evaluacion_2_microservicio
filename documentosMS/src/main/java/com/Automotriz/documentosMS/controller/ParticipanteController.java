package com.Automotriz.documentosMS.controller;

import com.Automotriz.documentosMS.model.Participante;
import com.Automotriz.documentosMS.service.ParticipanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/participantes")
public class ParticipanteController {

    @Autowired
    private ParticipanteService service;

    @GetMapping
    public ResponseEntity<List<Participante>> listar() {
        List<Participante> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> buscar(@PathVariable @NonNull Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/contrato/{contratoId}")
    public ResponseEntity<List<Participante>> porContrato(@PathVariable Integer contratoId) {
        List<Participante> lista = service.buscarPorContrato(contratoId);
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Participante> guardar(@RequestBody Participante participante) {
        return ResponseEntity.ok(service.guardar(participante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
