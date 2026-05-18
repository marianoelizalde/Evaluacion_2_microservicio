package com.Automotriz.usuarioMS;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsuarioMsApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsuarioMsApplication.class, args);
    }
}



//*  ENDPOINTS:
//*  GET    :8080/api/v1/usuarios              → lista todos
//*  GET    :8080/api/v1/usuarios/clientes     → solo tipo=1
//*  GET    :8080/api/v1/usuarios/empleados    → solo tipo=2
//*  GET    :8080/api/v1/usuarios/{id}         → busca por ID
//*  GET    :8080/api/v1/usuarios/rut/{rut}    → busca por RUT
//*  GET    :8080/api/v1/usuarios/dto/{id}     → * para otros MS (sin claveUnica)
//*  POST   :8080/api/v1/usuarios              → crea usuario
//*  PUT    :8080/api/v1/usuarios/{id}         → actualiza
//*  DELETE :8080/api/v1/usuarios/{id}         → elimina
//*ENDPOINTS:
 //*  GET    :8080/api/v1/clientes              → lista todos
 //*  GET    :8080/api/v1/clientes/{id}         → busca por ID
 //*  GET    :8080/api/v1/clientes/usuario/{id} → cliente de ese usuario
 //*  GET    :8080/api/v1/clientes/dto/{id}     → * para reservasMS
 //*  POST   :8080/api/v1/clientes              → crea (body: tipoCliente, descripcion, usuario:{id:1})
 //*  PUT    :8080/api/v1/clientes/{id}         → actualiza
 //*  DELETE :8080/api/v1/clientes/{id}         → elimina
 //*ENDPOINTS:
 //*  GET    :8080/api/v1/empleados                → lista todos
 //*  GET    :8080/api/v1/empleados/{id}           → busca por ID
 //*  GET    :8080/api/v1/empleados/sucursal/{id}  → empleados de esa sucursal
 //*  GET    :8080/api/v1/empleados/dto/{id}       → * para otros MS
 //*  POST   :8080/api/v1/empleados                → crea (body: descripcion, sucursalId, infoBancaria, usuario:{id:2})
 //*  PUT    :8080/api/v1/empleados/{id}           → actualiza
 //*  DELETE :8080/api/v1/empleados/{id}           → elimina
 //*ENDPOINTS:
 //*  GET    :8080/api/v1/antecedentes              → lista todos
 //*  GET    :8080/api/v1/antecedentes/{id}         → busca por ID
 //*  GET    :8080/api/v1/antecedentes/cliente/{id} → antecedentes del cliente
 //*  POST   :8080/api/v1/antecedentes              → crea (body: estadoLicencia, registroMultas, cliente:{id:1})
 //*  PUT    :8080/api/v1/antecedentes/{id}         → actualiza (ej: agregar multa)
 //*  DELETE :8080/api/v1/antecedentes/{id}         → elimina
 