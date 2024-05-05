package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.repositories.IAccesoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AccesoService {
    @Autowired
    private IAccesoRepository accesoRepository;

    public Acceso save(Acceso acceso) {
        acceso.setFechaCreacion(LocalDate.now());
        return accesoRepository.save(acceso);
    }

    public Acceso findById(Long id) {
        return accesoRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        accesoRepository.deleteById(id);
    }

    public List<Acceso> findAll() {
        return accesoRepository.findAll();
    }
}
