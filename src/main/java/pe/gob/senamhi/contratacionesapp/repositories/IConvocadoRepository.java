package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Convocado;

public interface IConvocadoRepository extends JpaRepository<Convocado, Long> {
}
