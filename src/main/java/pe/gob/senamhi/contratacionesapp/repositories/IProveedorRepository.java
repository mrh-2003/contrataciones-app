package pe.gob.senamhi.contratacionesapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.gob.senamhi.contratacionesapp.entities.Proveedor;

public interface IProveedorRepository extends JpaRepository<Proveedor, Long> {
    Proveedor findByDniRuc(String dniRuc);
}
