package com.Automotriz.usuarioMS.service;

import com.Automotriz.usuarioMS.model.Cliente;
import com.Automotriz.usuarioMS.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j; // 1. ← IMPORTACIÓN OBLIGATORIA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j 
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listar() {
        log.info("Listando todos los clientes"); 
        return clienteRepository.findAll();
    }

    public Cliente buscarPorId(Integer id) {
        log.info("Buscando cliente por ID: {}", id);
        return clienteRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Cliente con ID {} no fue encontrado", id); 
                    return new RuntimeException("Cliente no encontrado");
                });
    }

    public Cliente buscarPorUsuario(Integer usuarioId) {
        log.info("Buscando cliente asociado al usuario ID: {}", usuarioId);
        return clienteRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> {
                    log.warn("Cliente asociado al usuario ID {} no fue encontrado", usuarioId); 
                    return new RuntimeException("Cliente no encontrado");
                });
    }

    public Cliente guardar(Cliente cliente) {
        log.info("Guardando un nuevo cliente en el sistema (Tipo: {})", cliente.getTipoCliente());
        return clienteRepository.save(cliente);
    }

    public Cliente actualizar(Integer id, Cliente datos) {
        log.info("Iniciando actualización del cliente con ID: {}", id);
        Cliente cliente = buscarPorId(id);
        
        cliente.setTipoCliente(datos.getTipoCliente());
        cliente.setDescripcion(datos.getDescripcion());
        
        log.info("Cliente con ID {} actualizado exitosamente", id);
        return clienteRepository.save(cliente);
    }

    public void eliminar(Integer id) {
        log.info("Intentando eliminar cliente con ID: {}", id);
        if (!clienteRepository.existsById(id)) {
            log.error("Error al eliminar: El cliente con ID {} no existe en el sistema", id); 
            throw new RuntimeException("Cliente no existe");
        }
        clienteRepository.deleteById(id);
        log.info("Cliente con ID {} eliminado correctamente", id);
    }
}