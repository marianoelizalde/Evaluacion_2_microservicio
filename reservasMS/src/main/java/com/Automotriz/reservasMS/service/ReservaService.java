package com.Automotriz.reservasMS.service;

import com.Automotriz.reservasMS.client.*;
import com.Automotriz.reservasMS.dto.*;
import com.Automotriz.reservasMS.model.Reserva;
import com.Automotriz.reservasMS.repository.ReservaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class ReservaService {

    @Autowired private ReservaRepository reservaRepository;
    @Autowired private UsuarioClient usuarioClient;
    @Autowired private VehiculoClient vehiculoClient;
    @Autowired private SucursalClient sucursalClient;

    public List<Reserva> listar() {
        log.info("Listando todas las reservas");
        List<Reserva> reservas = reservaRepository.findAll();
        log.info("Se encontraron {} reservas", reservas.size());
        return reservas;
    }

    public Reserva buscarPorId(Integer id) {
        log.info("Buscando reserva con id: {}", id);
        return reservaRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Reserva con id {} no encontrada", id);
                    return new RuntimeException("Reserva no encontrada");
                });
    }

    public List<Reserva> buscarPorCliente(String rutCliente) {
        log.info("Buscando reservas del cliente: {}", rutCliente);
        return reservaRepository.findByRutCliente(rutCliente);
    }

    public List<Reserva> buscarPorEstado(String estado) {
        log.info("Buscando reservas con estado: {}", estado);
        return reservaRepository.findByEstado(estado);
    }

    public Reserva guardar(Reserva reserva) {
        log.info("Creando nueva reserva para cliente: {} con vehiculo: {}",
                reserva.getRutCliente(), reserva.getPatente());
        reserva.setEstado("PENDIENTE");
        Reserva guardada = reservaRepository.save(reserva);
        log.info("Reserva creada con id: {} en estado PENDIENTE", guardada.getId());
        return guardada;
    }

    public Reserva actualizar(Integer id, Reserva datos) {
        log.info("Actualizando reserva con id: {}", id);
        Reserva reserva = buscarPorId(id);
        reserva.setRutCliente(datos.getRutCliente());
        reserva.setPatente(datos.getPatente());
        reserva.setSucursalId(datos.getSucursalId());
        reserva.setFechaInicio(datos.getFechaInicio());
        reserva.setFechaFin(datos.getFechaFin());
        reserva.setEstado(datos.getEstado());
        Reserva actualizada = reservaRepository.save(reserva);
        log.info("Reserva con id {} actualizada al estado: {}", id, actualizada.getEstado());
        return actualizada;
    }

    public void eliminar(Integer id) {
        log.info("Eliminando reserva con id: {}", id);
        if (!reservaRepository.existsById(id)) {
            log.error("No se puede eliminar: reserva con id {} no existe", id);
            throw new RuntimeException("Reserva no existe");
        }
        reservaRepository.deleteById(id);
        log.info("Reserva con id {} eliminada correctamente", id);
    }

    public ReservaDetalleDTO obtenerDetalle(Integer id) {
        log.info("Obteniendo detalle completo de reserva con id: {}", id);
        Reserva reserva = buscarPorId(id);

        log.info("Consultando datos de cliente: {} en usuarioMS", reserva.getRutCliente());
        UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorRut(reserva.getRutCliente());

        log.info("Consultando datos de vehiculo: {} en vehiculoMS", reserva.getPatente());
        VehiculoDTO vehiculo = vehiculoClient.obtenerVehiculoPorPatente(reserva.getPatente());

        log.info("Consultando datos de sucursal: {} en sucursalMS", reserva.getSucursalId());
        SucursalDTO sucursal = sucursalClient.obtenerSucursal(reserva.getSucursalId());

        log.info("Detalle de reserva {} armado correctamente", id);
        return new ReservaDetalleDTO(reserva.getId(), reserva.getEstado(),
                reserva.getFechaInicio(), reserva.getFechaFin(), usuario, vehiculo, sucursal);
    }
}
