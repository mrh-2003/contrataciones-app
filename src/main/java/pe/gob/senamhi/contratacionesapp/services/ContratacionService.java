package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;
import pe.gob.senamhi.contratacionesapp.repositories.IContratacionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContratacionService {
    @Autowired
    private IContratacionRepository contratacionRepository;

    public Contratacion save(Contratacion contratacion) {
        contratacion.setFechaCreacion(LocalDate.now());
        return contratacionRepository.save(contratacion);
    }

    public Contratacion update(Contratacion contratacion) {
        return contratacionRepository.save(contratacion);
    }

    public Contratacion findById(Long id) {
        return contratacionRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        contratacionRepository.deleteById(id);
    }

    public List<Contratacion> findAll() {
        return contratacionRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }
    public List<Contratacion> findAllByCodigoAcceso(Long id) {
        return contratacionRepository.findAllByCodigoAcceso(id);
    }
}
