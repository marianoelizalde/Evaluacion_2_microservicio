package com.Automotriz.usuarioMS.controller;

import com.Automotriz.usuarioMS.dto.UsuarioDTO;
import com.Automotriz.usuarioMS.model.Usuario;
import com.Automotriz.usuarioMS.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> lista = service.listar();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Usuario>> listarClientes() {
        List<Usuario> lista = service.listarClientes();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/empleados")
    public ResponseEntity<List<Usuario>> listarEmpleados() {
        List<Usuario> lista = service.listarEmpleados();
        if (lista.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        try { return ResponseEntity.ok(service.buscarPorId(id)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/rut/{rut}")
    public ResponseEntity<Usuario> buscarPorRut(@PathVariable String rut) {
        try { return ResponseEntity.ok(service.buscarPorRut(rut)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @GetMapping("/dto/{id}")
    public ResponseEntity<UsuarioDTO> obtenerDTO(@PathVariable Integer id) {
        try {
            Usuario u = service.buscarPorId(id);
            return ResponseEntity.ok(new UsuarioDTO(u.getId(), u.getRut(), u.getNombre(),
                    u.getCorreo(), u.getTelefono(), u.getTipo()));
        } catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    // @Valid → activa las validaciones del modelo antes de procesar
    // Si hay error de validación → Spring devuelve 400 Bad Request automáticamente
    @PostMapping
    public ResponseEntity<Usuario> guardar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.guardar(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id,
                                               @Valid @RequestBody Usuario usuario) {
        try { return ResponseEntity.ok(service.actualizar(id, usuario)); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try { service.eliminar(id); return ResponseEntity.noContent().build(); }
        catch (RuntimeException e) { return ResponseEntity.notFound().build(); }
    }
}
