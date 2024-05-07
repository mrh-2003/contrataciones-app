package pe.gob.senamhi.contratacionesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContratacionesAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContratacionesAppApplication.class, args);
    }
//
//    @Bean
//    public CommandLineRunner createRole(RoleRepository roleRepository) {
//        return (args) -> {
//            roleRepository.save(new Role("ROLE_ADMIN"));
//            roleRepository.save(new Role("ROLE_USER"));
//        };
//    }
}
