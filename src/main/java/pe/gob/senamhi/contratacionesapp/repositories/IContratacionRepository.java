package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;

import java.util.List;

public interface IContratacionRepository extends JpaRepository<Contratacion, Long> {
    List<Contratacion> findAllByCodigoAcceso(Long id);
}
