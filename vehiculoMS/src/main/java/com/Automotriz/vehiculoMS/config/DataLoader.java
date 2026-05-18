package com.Automotriz.vehiculoMS.config;

import com.Automotriz.vehiculoMS.model.*;
import com.Automotriz.vehiculoMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import java.time.LocalDate;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(
            VehiculoRepository vehiculoRepo,
            MantencionRepository mantencionRepo) {

        return args -> {
            if (vehiculoRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {
                // Vehículos
                Vehiculo v1 = new Vehiculo(null, "Corolla", "XEI", "Toyota", 2022, "DISPONIBLE", "ABCD12");
                Vehiculo v2 = new Vehiculo(null, "Civic", "EX", "Honda", 2021, "DISPONIBLE", "EFGH34");
                Vehiculo v3 = new Vehiculo(null, "Mazda3", "GT", "Mazda", 2023, "EN_MANTENCION", "IJKL56");

                vehiculoRepo.save(v1);
                vehiculoRepo.save(v2);
                vehiculoRepo.save(v3);

                // Mantenciones
                Mantencion m1 = new Mantencion(null, LocalDate.now(), 150000.0, v3);
                mantencionRepo.save(m1);

                System.out.println("✅ Datos de prueba cargados en vehiculoMS");
            }
        };
    }
}