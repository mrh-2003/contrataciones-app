package pe.gob.senamhi.contratacionesapp.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
	Role findByRole(String role);

}
