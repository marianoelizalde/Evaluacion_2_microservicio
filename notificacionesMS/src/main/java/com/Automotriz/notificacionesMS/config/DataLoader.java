package com.Automotriz.notificacionesMS.config;

import com.Automotriz.notificacionesMS.model.*;
import com.Automotriz.notificacionesMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(NotificacionRepository notificacionRepo) {

        return args -> {

            if (notificacionRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {

                Notificacion n1 = new Notificacion(null, "EMAIL", 1, "Su reserva ha sido confirmada",          "2026-05-01", "ENVIADA");
                Notificacion n2 = new Notificacion(null, "SMS",   2, "Su reserva está pendiente de pago",      "2026-05-05", "ENVIADA");
                Notificacion n3 = new Notificacion(null, "EMAIL", 3, "Recuerde devolver el vehículo mañana",   "2026-05-14", "PENDIENTE");
                Notificacion n4 = new Notificacion(null, "PUSH",  1, "Su arriendo ha finalizado exitosamente", "2026-04-05", "ENVIADA");

                notificacionRepo.save(n1);
                notificacionRepo.save(n2);
                notificacionRepo.save(n3);
                notificacionRepo.save(n4);

                System.out.println("✅ Datos de notificacionesMS cargados correctamente");
            }
        };
    }
}
