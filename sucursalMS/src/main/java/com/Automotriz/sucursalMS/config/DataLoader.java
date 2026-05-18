package com.Automotriz.sucursalMS.config;

import com.Automotriz.sucursalMS.model.*;
import com.Automotriz.sucursalMS.repository.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataBase(SucursalRepository sucursalRepo) {

        return args -> {

            if (sucursalRepo.count() > 0) {
                System.out.println("No se cargó nada porque ya habían datos");
            } else {

                Sucursal s1 = new Sucursal(null, "Sucursal Centro",     "Av. Libertador 123",    "Santiago",    8);
                Sucursal s2 = new Sucursal(null, "Sucursal Providencia", "Av. Providencia 1234",  "Providencia", 5);
                Sucursal s3 = new Sucursal(null, "Sucursal Ñuñoa",      "Av. Irarrázaval 890",   "Ñuñoa",       4);
                Sucursal s4 = new Sucursal(null, "Sucursal Maipú",      "Av. Pajaritos 456",     "Maipú",       6);
                Sucursal s5 = new Sucursal(null, "Sucursal Las Condes", "Av. Apoquindo 789",     "Las Condes",  7);

                sucursalRepo.save(s1);
                sucursalRepo.save(s2);
                sucursalRepo.save(s3);
                sucursalRepo.save(s4);
                sucursalRepo.save(s5);

                System.out.println("✅ Datos de sucursalMS cargados correctamente");
            }
        };
    }
}
