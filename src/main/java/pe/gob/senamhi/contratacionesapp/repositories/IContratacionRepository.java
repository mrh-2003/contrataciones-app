package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;

public interface IContratacionRepository extends JpaRepository<Contratacion, Long> {
}
