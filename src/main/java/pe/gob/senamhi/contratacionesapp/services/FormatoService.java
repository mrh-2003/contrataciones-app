package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Formato;
import pe.gob.senamhi.contratacionesapp.repositories.IFormatoRepository;

import java.util.List;

@Service
public class FormatoService {
    @Autowired
    private IFormatoRepository formatoRepository;
    public Formato save(Formato formato) {
        return formatoRepository.save(formato);
    }
    public Formato findById(Long id) {
        return formatoRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id) {
        formatoRepository.deleteById(id);
    }
    public List<Formato> findAll() {
        return formatoRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
    }

}
