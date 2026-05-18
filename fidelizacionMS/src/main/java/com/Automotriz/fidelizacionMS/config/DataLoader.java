package com.Automotriz.fidelizacionMS.config;

import com.Automotriz.fidelizacionMS.model.*;
import com.Automotriz.fidelizacionMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(FidelizacionRepository fidelizacionRepo) {

        return args -> {

            if (fidelizacionRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {

                // ⚠️ Los rut deben existir en usuarioMS
                Fidelizacion f1 = new Fidelizacion(null, "12345678-9", 5200, "ORO");
                Fidelizacion f2 = new Fidelizacion(null, "98765432-1", 1500, "PLATA");
                Fidelizacion f3 = new Fidelizacion(null, "11111111-1", 300,  "BRONCE");

                fidelizacionRepo.save(f1);
                fidelizacionRepo.save(f2);
                fidelizacionRepo.save(f3);

                System.out.println("Datos de fidelizacionMS cargados correctamente");
            }
        };
    }
}
