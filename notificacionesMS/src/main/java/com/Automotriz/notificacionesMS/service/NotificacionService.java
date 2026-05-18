package com.Automotriz.notificacionesMS.service;

import com.Automotriz.notificacionesMS.model.Notificacion;
import com.Automotriz.notificacionesMS.repository.NotificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificacionService {

    @Autowired
    private NotificacionRepository notificacionRepository;

    public List<Notificacion> listar() { return notificacionRepository.findAll(); }

    public Notificacion buscarPorId(Integer id) {
        return notificacionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Notificacion no encontrada"));
    }

    public List<Notificacion> buscarPorReserva(Integer reservaId) {
        return notificacionRepository.findByReservaId(reservaId);
    }

    public List<Notificacion> buscarPorEstado(String estado) {
        return notificacionRepository.findByEstado(estado);
    }

    public Notificacion guardar(Notificacion notificacion) {
        notificacion.setEstado("PENDIENTE");
        notificacion.setFechaNotificacion("2026-05-12"); // String fija en vez de LocalDate.now()
        return notificacionRepository.save(notificacion);
    }

    public Notificacion actualizar(Integer id, Notificacion datos) {
        Notificacion notificacion = buscarPorId(id);
        notificacion.setEstado(datos.getEstado());
        notificacion.setMensaje(datos.getMensaje());
        return notificacionRepository.save(notificacion);
    }

    public void eliminar(Integer id) {
        if (!notificacionRepository.existsById(id)) throw new RuntimeException("Notificacion no existe");
        notificacionRepository.deleteById(id);
    }
}
