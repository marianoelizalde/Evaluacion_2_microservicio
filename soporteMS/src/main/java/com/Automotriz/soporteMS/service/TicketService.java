package com.Automotriz.soporteMS.service;

import com.Automotriz.soporteMS.model.Ticket;
import com.Automotriz.soporteMS.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public List<Ticket> listar() { return ticketRepository.findAll(); }

    public Ticket buscarPorId(Integer id) {
        return ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket no encontrado"));
    }

    public List<Ticket> buscarPorReserva(Integer reservaId) {
        return ticketRepository.findByReservaId(reservaId);
    }

    public List<Ticket> buscarPorEstado(String estado) {
        return ticketRepository.findByEstado(estado);
    }

    public Ticket guardar(Ticket ticket) {
        ticket.setEstado("ABIERTO");
        ticket.setFechaCreacion("2026-05-12"); // String fija en vez de LocalDate.now()
        return ticketRepository.save(ticket);
    }

    public Ticket actualizar(Integer id, Ticket datos) {
        Ticket ticket = buscarPorId(id);
        ticket.setAsunto(datos.getAsunto());
        ticket.setDescripcion(datos.getDescripcion());
        ticket.setEstado(datos.getEstado());
        return ticketRepository.save(ticket);
    }

    public void eliminar(Integer id) {
        if (!ticketRepository.existsById(id)) throw new RuntimeException("Ticket no existe");
        ticketRepository.deleteById(id);
    }
}
