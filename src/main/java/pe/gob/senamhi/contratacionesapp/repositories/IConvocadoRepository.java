package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Convocado;

import java.util.List;

public interface IConvocadoRepository extends JpaRepository<Convocado, Long> {
    public List<Convocado> findAllByCodigoContratacion(Long codigo);
    public boolean existsAllByDniRucAndCodigoContratacion(String dniRuc, Long codigo);
}
