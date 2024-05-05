package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Formato;

public interface IFormatoRepository extends JpaRepository<Formato, Long> {
}
