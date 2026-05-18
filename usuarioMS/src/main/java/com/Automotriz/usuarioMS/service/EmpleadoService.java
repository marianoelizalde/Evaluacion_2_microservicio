package com.Automotriz.usuarioMS.service;

import com.Automotriz.usuarioMS.model.Empleado;
import com.Automotriz.usuarioMS.repository.EmpleadoRepository;
import lombok.extern.slf4j.Slf4j; // 1. ← IMPORTACIÓN OBLIGATORIA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j // 2. ← ANOTACIÓN PARA HABILITAR EL log
@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public List<Empleado> listar() {
        log.info("Listando todos los empleados"); 
        return empleadoRepository.findAll();
    }

    public Empleado buscarPorId(Integer id) {
        log.info("Buscando empleado por ID: {}", id);
        return empleadoRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Empleado con ID {} no fue encontrado", id); 
                    return new RuntimeException("Empleado no encontrado");
                });
    }

    public Empleado buscarPorUsuario(Integer usuarioId) {
        log.info("Buscando empleado asociado al usuario ID: {}", usuarioId);
        return empleadoRepository.findByUsuarioId(usuarioId)
                .orElseThrow(() -> {
                    log.warn("Empleado asociado al usuario ID {} no fue encontrado", usuarioId); 
                    return new RuntimeException("Empleado no encontrado");
                });
    }

    public List<Empleado> buscarPorSucursal(Integer sucursalId) {
        log.info("Buscando empleados pertenecientes a la sucursal ID: {}", sucursalId);
        return empleadoRepository.findBySucursalId(sucursalId);
    }

    public Empleado guardar(Empleado empleado) {
        log.info("Guardando un nuevo empleado en el sistema");
        return empleadoRepository.save(empleado);
    }

    public Empleado actualizar(Integer id, Empleado datos) {
        log.info("Iniciando actualización del empleado con ID: {}", id);
        Empleado empleado = buscarPorId(id);
        
        empleado.setDescripcion(datos.getDescripcion());
        empleado.setSucursalId(datos.getSucursalId());
        empleado.setInfoBancaria(datos.getInfoBancaria());
        
        log.info("Empleado con ID {} actualizado exitosamente", id);
        return empleadoRepository.save(empleado);
    }

    public void eliminar(Integer id) {
        log.info("Intentando eliminar empleado con ID: {}", id);
        if (!empleadoRepository.existsById(id)) {
            log.error("Error al eliminar: El empleado con ID {} no existe en el sistema", id); 
            throw new RuntimeException("Empleado no existe");
        }
        empleadoRepository.deleteById(id);
        log.info("Empleado con ID {} eliminado correctamente", id);
    }
}