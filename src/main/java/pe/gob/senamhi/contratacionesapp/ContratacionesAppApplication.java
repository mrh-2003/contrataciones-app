package pe.gob.senamhi.contratacionesapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;
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
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    @Bean
    public CommandLineRunner createRole(IRoleRepository roleRepository,
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
                        "ADMINN",
                        passwordEncoder.encode("ADMINN"),
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
            }
        };
    }
}
