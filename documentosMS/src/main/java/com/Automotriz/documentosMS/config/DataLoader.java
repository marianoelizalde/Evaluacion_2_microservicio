

package com.Automotriz.documentosMS.config;

import com.Automotriz.documentosMS.model.*;
import com.Automotriz.documentosMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(
            ContratoRepository contratoRepo,
            ParticipanteRepository participanteRepo) {

        return args -> {

            if (contratoRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {

                // CONTRATOS
                Contrato con1 = new Contrato(null, 1, "2026-05-01", "FIRMADO",  "El cliente devuelve el vehículo en las mismas condiciones.");
                Contrato con2 = new Contrato(null, 2, "2026-05-05", "PENDIENTE", "El cliente se hace responsable de cualquier daño.");
                Contrato con3 = new Contrato(null, 3, "2026-05-08", "PENDIENTE", "Prohibido fumar dentro del vehículo.");

                contratoRepo.save(con1);
                contratoRepo.save(con2);
                contratoRepo.save(con3);

                // PARTICIPANTES
                Participante pa1 = new Participante(null, "12345678-9", "Juan Pérez",   "ARRENDATARIO", true,  con1);
                Participante pa2 = new Participante(null, "22222222-2", "Ana González", "EMPLEADO",     true,  con1);
                Participante pa3 = new Participante(null, "98765432-1", "María López",  "ARRENDATARIO", false, con2);
                Participante pa4 = new Participante(null, "33333333-3", "Pedro Ramírez","EMPLEADO",     false, con2);

                participanteRepo.save(pa1);
                participanteRepo.save(pa2);
                participanteRepo.save(pa3);
                participanteRepo.save(pa4);

                System.out.println("✅ Datos de documentosMS cargados correctamente");
            }
        };
    }
}
