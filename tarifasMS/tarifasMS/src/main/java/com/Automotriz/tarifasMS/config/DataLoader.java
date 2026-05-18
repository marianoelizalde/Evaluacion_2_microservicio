package com.Automotriz.tarifasMS.config;

import com.Automotriz.tarifasMS.model.*;
import com.Automotriz.tarifasMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(TarifaRepository tarifaRepo) {

        return args -> {

            if (tarifaRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {

                // vehiculoId debe existir en vehiculoMS
                Tarifa t1 = new Tarifa(null, 1, 45000.0, "NORMAL",  "ACTIVO");
                Tarifa t2 = new Tarifa(null, 1, 65000.0, "ALTA",    "ACTIVO");
                Tarifa t3 = new Tarifa(null, 2, 38000.0, "BAJA",    "ACTIVO");
                Tarifa t4 = new Tarifa(null, 2, 55000.0, "NORMAL",  "ACTIVO");
                Tarifa t5 = new Tarifa(null, 3, 72000.0, "ALTA",    "INACTIVO");

                tarifaRepo.save(t1);
                tarifaRepo.save(t2);
                tarifaRepo.save(t3);
                tarifaRepo.save(t4);
                tarifaRepo.save(t5);

                System.out.println("Datos de tarifasMS cargados correctamente");
            }
        };
    }
}