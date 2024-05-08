package pe.gob.senamhi.contratacionesapp;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pe.gob.senamhi.contratacionesapp.entities.Role;
import pe.gob.senamhi.contratacionesapp.repositories.IRoleRepository;

@SpringBootApplication
public class ContratacionesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratacionesAppApplication.class, args);
    }
//
//    @Bean
//    public CommandLineRunner createRole(IRoleRepository roleRepository) {
//        return (args) -> {
//            roleRepository.save(new Role("ROLE_ADMINISTRADOR"));
//            roleRepository.save(new Role("ROLE_USUARIO"));
//        };
//    }
}
