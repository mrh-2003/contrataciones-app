package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;

public interface IAccesoRepository extends JpaRepository<Acceso, Long> {
}
