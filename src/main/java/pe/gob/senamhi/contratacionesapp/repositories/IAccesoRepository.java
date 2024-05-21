package pe.gob.senamhi.contratacionesapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;

import java.util.List;

public interface IAccesoRepository extends JpaRepository<Acceso, Long>{
	Acceso findByUsuario(String username);
}
