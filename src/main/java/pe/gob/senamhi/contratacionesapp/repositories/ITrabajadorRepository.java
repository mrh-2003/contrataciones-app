package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;

public interface ITrabajadorRepository extends JpaRepository<Trabajador, Long> {
    Trabajador findByDni(String dni);
}
