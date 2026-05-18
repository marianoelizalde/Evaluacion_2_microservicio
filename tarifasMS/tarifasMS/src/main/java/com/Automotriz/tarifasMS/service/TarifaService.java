package com.Automotriz.tarifasMS.service;

import com.Automotriz.tarifasMS.model.Tarifa;
import com.Automotriz.tarifasMS.repository.TarifaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TarifaService {

    @Autowired
    private TarifaRepository tarifaRepository;

    public List<Tarifa> listar() {
        return tarifaRepository.findAll();
    }

    public Tarifa buscarPorId(Integer id) {
        return tarifaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tarifa no encontrada"));
    }

    public List<Tarifa> buscarPorVehiculo(Integer vehiculoId) {
        return tarifaRepository.findByVehiculoId(vehiculoId);
    }

    public List<Tarifa> buscarPorTemporada(String temporada) {
        return tarifaRepository.findByTemporada(temporada);
    }

    public List<Tarifa> buscarActivas() {
        return tarifaRepository.findByEstado("ACTIVO");
    }

    public Tarifa guardar(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Tarifa actualizar(Integer id, Tarifa datos) {
        Tarifa tarifa = buscarPorId(id);
        tarifa.setVehiculoId(datos.getVehiculoId());
        tarifa.setPrecioDia(datos.getPrecioDia());
        tarifa.setTemporada(datos.getTemporada());
        tarifa.setEstado(datos.getEstado());
        return tarifaRepository.save(tarifa);
    }

    public void eliminar(Integer id) {
        if (!tarifaRepository.existsById(id)) throw new RuntimeException("Tarifa no existe");
        tarifaRepository.deleteById(id);
    }
}