package com.Automotriz.pagosMS.service;

import com.Automotriz.pagosMS.model.Pago;
import com.Automotriz.pagosMS.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    public List<Pago> listar() { return pagoRepository.findAll(); }

    public Pago buscarPorId(Integer id) {
        return pagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    public List<Pago> buscarPorReserva(Integer reservaId) {
        return pagoRepository.findByReservaId(reservaId);
    }

    public List<Pago> buscarPorEstado(String estadoPago) {
        return pagoRepository.findByEstadoPago(estadoPago);
    }

    public Pago guardar(Pago pago) {
        pago.setEstadoPago("PENDIENTE");
        return pagoRepository.save(pago);
    }

    public Pago actualizar(Integer id, Pago datos) {
        Pago pago = buscarPorId(id);
        pago.setMonto(datos.getMonto());
        pago.setEstadoPago(datos.getEstadoPago());
        pago.setMetodoPago(datos.getMetodoPago());
        pago.setFechaPago(datos.getFechaPago());
        return pagoRepository.save(pago);
    }

    public void eliminar(Integer id) {
        if (!pagoRepository.existsById(id)) throw new RuntimeException("Pago no existe");
        pagoRepository.deleteById(id);
    }
}
