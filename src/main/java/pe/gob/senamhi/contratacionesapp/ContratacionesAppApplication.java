package pe.gob.senamhi.contratacionesapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.entities.Role;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;
import pe.gob.senamhi.contratacionesapp.repositories.IAccesoRepository;
import pe.gob.senamhi.contratacionesapp.repositories.IRoleRepository;
import pe.gob.senamhi.contratacionesapp.repositories.ITrabajadorRepository;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ContratacionesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratacionesAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner createRole(IRoleRepository roleRepository,
                                        ITrabajadorRepository trabajadorRepository,
                                        IAccesoRepository accesoRepository,
                                        BCryptPasswordEncoder passwordEncoder) {
        return (args) -> {
            if(roleRepository.count() ==0){
                Role role = roleRepository.save(new Role("ROLE_ADMINISTRADOR"));
                roleRepository.save(new Role("ROLE_PERSONAL_LOGISTICO"));
                Set<Role> roles = new HashSet<Role>();
                roles.add(role);
                accesoRepository.save(new Acceso(
                        Long.valueOf(0),
                        "adminn",
                        passwordEncoder.encode("adminn"),
                        "ADMINISTRADOR",
                        "0",
                        "ADMINISTRADOR",
                        "0",
                        "CENTRAL",
                        "00000000",
                        "0",
                        LocalDate.now(),
                        true,
                        roles
                ));

                // Inserts para trabajadores
                trabajadorRepository.save(new Trabajador("12345678", "Jose Flores", "Perez", "Flores", "001", "001", "Lima", "001", "Administrador"));
                trabajadorRepository.save(new Trabajador("87654321", "Ana Gomez", "Garcia", "Gomez", "002", "002", "Arequipa", "002", "Contador"));
                trabajadorRepository.save(new Trabajador("19283746", "Juan Torres", "Gonzales", "Torres", "003", "003", "Cusco", "003", "Analista"));
            }
        };
    }
}
