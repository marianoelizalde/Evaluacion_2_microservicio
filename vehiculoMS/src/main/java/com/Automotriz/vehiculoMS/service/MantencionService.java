package com.Automotriz.vehiculoMS.service;

import com.Automotriz.vehiculoMS.model.Mantencion;
import com.Automotriz.vehiculoMS.repository.MantencionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MantencionService {

    @Autowired
    private MantencionRepository mantencionRepository;

    public List<Mantencion> listar() { return mantencionRepository.findAll(); }

    public Mantencion buscarPorId(Integer id) {
        return mantencionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mantencion no encontrada"));
    }

    public List<Mantencion> buscarPorVehiculo(Integer vehiculoId) {
        return mantencionRepository.findByVehiculoId(vehiculoId);
    }

    public Mantencion guardar(Mantencion mantencion) { return mantencionRepository.save(mantencion); }

    public Mantencion actualizar(Integer id, Mantencion datos) {
        Mantencion m = buscarPorId(id);
        m.setFechaIngreso(datos.getFechaIngreso());
        m.setCosto(datos.getCosto());
        return mantencionRepository.save(m);
    }

    public void eliminar(Integer id) {
        if (!mantencionRepository.existsById(id)) throw new RuntimeException("Mantencion no existe");
        mantencionRepository.deleteById(id);
    }
}
