package com.Automotriz.usuarioMS.service;

import com.Automotriz.usuarioMS.model.Usuario;
import com.Automotriz.usuarioMS.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Slf4j
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> listar() {
        log.info("Listando todos los usuarios");
        List<Usuario> usuarios = usuarioRepository.findAll();
        log.info("Se encontraron {} usuarios", usuarios.size());
        return usuarios;
    }

    public List<Usuario> listarClientes() {
        log.info("Listando usuarios tipo CLIENTE");
        return usuarioRepository.findByTipo(1);
    }

    public List<Usuario> listarEmpleados() {
        log.info("Listando usuarios tipo EMPLEADO");
        return usuarioRepository.findByTipo(2);
    }

    public Usuario buscarPorId(Integer id) {
        log.info("Buscando usuario con id: {}", id);
        return usuarioRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuario con id {} no encontrado", id);
                    return new RuntimeException("Usuario no encontrado");
                });
    }

    public Usuario buscarPorRut(String rut) {
        log.info("Buscando usuario con rut: {}", rut);
        return usuarioRepository.findByRut(rut)
                .orElseThrow(() -> {
                    log.warn("Usuario con rut {} no encontrado", rut);
                    return new RuntimeException("Usuario no encontrado");
                });
    }

    public Usuario guardar(Usuario usuario) {
        log.info("Guardando nuevo usuario con rut: {}", usuario.getRut());
        Usuario guardado = usuarioRepository.save(usuario);
        log.info("Usuario guardado con id: {}", guardado.getId());
        return guardado;
    }

    public Usuario actualizar(Integer id, Usuario datos) {
        log.info("Actualizando usuario con id: {}", id);
        Usuario usuario = buscarPorId(id);
        usuario.setRut(datos.getRut());
        usuario.setNombre(datos.getNombre());
        usuario.setCorreo(datos.getCorreo());
        usuario.setTelefono(datos.getTelefono());
        usuario.setDireccion(datos.getDireccion());
        usuario.setTipo(datos.getTipo());
        usuario.setClaveUnica(datos.getClaveUnica());
        Usuario actualizado = usuarioRepository.save(usuario);
        log.info("Usuario con id {} actualizado correctamente", id);
        return actualizado;
    }

    public void eliminar(Integer id) {
        log.info("Eliminando usuario con id: {}", id);
        if (!usuarioRepository.existsById(id)) {
            log.error("No se puede eliminar: usuario con id {} no existe", id);
            throw new RuntimeException("Usuario no existe");
        }
        usuarioRepository.deleteById(id);
        log.info("Usuario con id {} eliminado correctamente", id);
    }
}
