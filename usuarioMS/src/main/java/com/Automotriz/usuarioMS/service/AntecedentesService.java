package com.Automotriz.usuarioMS.service;

import com.Automotriz.usuarioMS.model.Antecedentes;
import com.Automotriz.usuarioMS.repository.AntecedentesRepository;
import lombok.extern.slf4j.Slf4j; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j 
@Service
public class AntecedentesService {

    @Autowired
    private AntecedentesRepository antecedentesRepository;

    public List<Antecedentes> listar() {
        log.info("Listando todos los registros de antecedentes"); 
        return antecedentesRepository.findAll();
    }

    public Antecedentes buscarPorId(Integer id) {
        log.info("Buscando antecedentes por ID: {}", id);
        return antecedentesRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Antecedentes con ID {} no fueron encontrados", id); 
                    return new RuntimeException("Antecedentes no encontrados");
                });
    }

    public Antecedentes buscarPorCliente(Integer clienteId) {
        log.info("Buscando antecedentes asociados al cliente ID: {}", clienteId);
        return antecedentesRepository.findByClienteId(clienteId)
                .orElseThrow(() -> {
                    log.warn("Antecedentes del cliente ID {} no fueron encontrados", clienteId); 
                    return new RuntimeException("Antecedentes no encontrados");
                });
    }

    public Antecedentes guardar(Antecedentes antecedentes) {
        log.info("Guardando un nuevo registro de antecedentes en el sistema");
        return antecedentesRepository.save(antecedentes);
    }

    public Antecedentes actualizar(Integer id, Antecedentes datos) {
        log.info("Iniciando actualización de antecedentes con ID: {}", id);
        Antecedentes antecedentes = buscarPorId(id);
        
        antecedentes.setEstadoLicencia(datos.getEstadoLicencia());
        antecedentes.setRegistroMultas(datos.getRegistroMultas());
        
        log.info("Antecedentes con ID {} actualizados exitosamente", id);
        return antecedentesRepository.save(antecedentes);
    }

    public void eliminar(Integer id) {
        log.info("Intentando eliminar antecedentes con ID: {}", id);
        if (!antecedentesRepository.existsById(id)) {
            log.error("Error al eliminar: Los antecedentes con ID {} no existen en el sistema", id);
            throw new RuntimeException("Antecedentes no existen");
        }
        antecedentesRepository.deleteById(id);
        log.info("Antecedentes con ID {} eliminados correctamente", id);
    }
}