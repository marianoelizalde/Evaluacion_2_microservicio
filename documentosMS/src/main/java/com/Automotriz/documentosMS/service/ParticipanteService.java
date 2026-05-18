package com.Automotriz.documentosMS.service;

import com.Automotriz.documentosMS.model.Participante;
import com.Automotriz.documentosMS.repository.ParticipanteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipanteService {

    @Autowired
    private ParticipanteRepository participanteRepository;

    public List<Participante> listar() { return participanteRepository.findAll(); }

    public Participante buscarPorId(Integer id) {
        return participanteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Participante no encontrado"));
    }

    public List<Participante> buscarPorContrato(Integer contratoId) {
        return participanteRepository.findByContratoId(contratoId);
    }

    public Participante guardar(Participante participante) {
        return participanteRepository.save(participante);
    }

    public void eliminar(Integer id) {
        if (!participanteRepository.existsById(id)) throw new RuntimeException("Participante no existe");
        participanteRepository.deleteById(id);
    }
}
