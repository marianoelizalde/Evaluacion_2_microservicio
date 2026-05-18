package com.Automotriz.fidelizacionMS.service;

import com.Automotriz.fidelizacionMS.model.Fidelizacion;
import com.Automotriz.fidelizacionMS.repository.FidelizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FidelizacionService {

    @Autowired
    private FidelizacionRepository fidelizacionRepository;

    public List<Fidelizacion> listar() { return fidelizacionRepository.findAll(); }

    public Fidelizacion buscarPorId(Integer id) {
        return fidelizacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fidelizacion no encontrada"));
    }

    public Fidelizacion buscarPorRut(String rutCliente) {
        return fidelizacionRepository.findByRutCliente(rutCliente)
                .orElseThrow(() -> new RuntimeException("Fidelizacion no encontrada"));
    }

    public List<Fidelizacion> buscarPorNivel(String nivelSocio) {
        return fidelizacionRepository.findByNivelSocio(nivelSocio);
    }

    public Fidelizacion guardar(Fidelizacion fidelizacion) {
        fidelizacion.setPuntosAcumulados(0);
        fidelizacion.setNivelSocio("BRONCE");
        return fidelizacionRepository.save(fidelizacion);
    }

    public Fidelizacion agregarPuntos(String rutCliente, Integer puntos) {
        Fidelizacion f = buscarPorRut(rutCliente);
        f.setPuntosAcumulados(f.getPuntosAcumulados() + puntos);
        // Actualiza nivel según puntos
        int total = f.getPuntosAcumulados();
        if (total >= 10000) f.setNivelSocio("PLATINO");
        else if (total >= 5000) f.setNivelSocio("ORO");
        else if (total >= 1000) f.setNivelSocio("PLATA");
        else f.setNivelSocio("BRONCE");
        return fidelizacionRepository.save(f);
    }

    public void eliminar(Integer id) {
        if (!fidelizacionRepository.existsById(id)) throw new RuntimeException("Fidelizacion no existe");
        fidelizacionRepository.deleteById(id);
    }
}
