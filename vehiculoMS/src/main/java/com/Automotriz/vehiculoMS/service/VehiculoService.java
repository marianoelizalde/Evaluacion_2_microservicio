package com.Automotriz.vehiculoMS.service;

import com.Automotriz.vehiculoMS.model.Vehiculo;
import com.Automotriz.vehiculoMS.repository.VehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VehiculoService {

    @Autowired
    private VehiculoRepository vehiculoRepository;

    public List<Vehiculo> listar() { return vehiculoRepository.findAll(); }
    public List<Vehiculo> listarDisponibles() { return vehiculoRepository.findByEstado("DISPONIBLE"); }

    public Vehiculo buscarPorId(Integer id) {
        return vehiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));
    }

    public Vehiculo buscarPorPatente(String patente) {
        return vehiculoRepository.findByPatente(patente)
                .orElseThrow(() -> new RuntimeException("Vehiculo no encontrado"));
    }

    public Vehiculo guardar(Vehiculo vehiculo) { return vehiculoRepository.save(vehiculo); }

    public Vehiculo actualizar(Integer id, Vehiculo datos) {
        Vehiculo vehiculo = buscarPorId(id);
        vehiculo.setNombre(datos.getNombre());
        vehiculo.setModelo(datos.getModelo());
        vehiculo.setMarca(datos.getMarca());
        vehiculo.setAnio(datos.getAnio());
        vehiculo.setEstado(datos.getEstado());
        vehiculo.setPatente(datos.getPatente());
        return vehiculoRepository.save(vehiculo);
    }

    public void eliminar(Integer id) {
        if (!vehiculoRepository.existsById(id)) throw new RuntimeException("Vehiculo no existe");
        vehiculoRepository.deleteById(id);
    }
}
