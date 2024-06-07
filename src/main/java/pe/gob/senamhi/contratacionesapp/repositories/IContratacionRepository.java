package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;

import java.util.List;

public interface IContratacionRepository extends JpaRepository<Contratacion, Long> {
    List<Contratacion> findAllByCodigoAcceso(Long id);
    @Query("SELECT c FROM Contratacion c WHERE YEAR(c.fechaPublicacion) = :year AND c.fechaPublicacion <= CURRENT_DATE")
    List<Contratacion> findAllByFechaPublicacionYear(@Param("year") int year);

}
