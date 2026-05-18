package com.Automotriz.usuarioMS.controller;

import com.Automotriz.usuarioMS.dto.ClienteDTO;
import com.Automotriz.usuarioMS.model.Cliente;
import com.Automotriz.usuarioMS.service.ClienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        List<Cliente> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.buscarPorId(id));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<Cliente> porUsuario(@PathVariable Integer usuarioId) {
        try {
            return ResponseEntity.ok(service.buscarPorUsuario(usuarioId));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<ClienteDTO> obtenerDTO(@PathVariable Integer id) {
        try {
            Cliente c = service.buscarPorId(id);
            
            // ✅ Constructor corregido: (id, tipoCliente, descripcion, usuarioId, rut, nombre, correo, telefono)
            ClienteDTO dto = new ClienteDTO(
                c.getId(),
                c.getTipoCliente(),
                c.getDescripcion(),
                c.getUsuario().getId(),
                c.getUsuario().getRut(),
                c.getUsuario().getNombre(),
                c.getUsuario().getCorreo(),
                c.getUsuario().getTelefono()
            );
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping

    public ResponseEntity<Cliente> guardar(@Valid @RequestBody Cliente cliente) {
        return ResponseEntity.ok(service.guardar(cliente));
    }

    @PutMapping("/{id}")
    
    public ResponseEntity<Cliente> actualizar(@PathVariable Integer id, @Valid @RequestBody Cliente cliente) {
        try {
            return ResponseEntity.ok(service.actualizar(id, cliente));
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