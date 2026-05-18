package com.Automotriz.documentosMS.service;

import com.Automotriz.documentosMS.model.Contrato;
import com.Automotriz.documentosMS.repository.ContratoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContratoService {

    @Autowired
    private ContratoRepository contratoRepository;

    public List<Contrato> listar() { return contratoRepository.findAll(); }

    public Contrato buscarPorId(@NonNull Integer id) {
        return contratoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contrato no encontrado"));
    }

    public List<Contrato> buscarPorReserva(Integer reservaId) {
        return contratoRepository.findByReservaId(reservaId);
    }

    public List<Contrato> buscarPorEstado(String estado) {
        return contratoRepository.findByEstado(estado);
    }

    public Contrato guardar(Contrato contrato) {
        contrato.setEstado("PENDIENTE");
        return contratoRepository.save(contrato);
    }

    public Contrato actualizar(@NonNull Integer id, Contrato datos) {
        Contrato contrato = buscarPorId(id);
        contrato.setEstado(datos.getEstado());
        contrato.setClausulas(datos.getClausulas());
        return contratoRepository.save(contrato);
    }

    public void eliminar(@NonNull Integer id) {
        if (!contratoRepository.existsById(id)) throw new RuntimeException("Contrato no existe");
        contratoRepository.deleteById(id);
    }
}
