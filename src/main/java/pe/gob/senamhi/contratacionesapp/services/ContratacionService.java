package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;
import pe.gob.senamhi.contratacionesapp.repositories.IContratacionRepository;

import java.util.List;

@Service
public class ContratacionService {
    @Autowired
    private IContratacionRepository contratacionRepository;

    public Contratacion save(Contratacion contratacion) {
        return contratacionRepository.save(contratacion);
    }

    public Contratacion findById(Long id) {
        return contratacionRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        contratacionRepository.deleteById(id);
    }

    public List<Contratacion> findAll() {
        return contratacionRepository.findAll();
    }
}
