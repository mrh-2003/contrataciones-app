package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Convocado;
import pe.gob.senamhi.contratacionesapp.repositories.IConvocadoRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ConvocadoService {
    @Autowired
    private IConvocadoRepository convocadoRepository;
    public List<Convocado> findAllByCodigoContratacion(Long codigo) {
        return convocadoRepository.findAllByCodigoContratacion(codigo);
    }
    public Convocado save(Convocado convocado) {
        convocado.setFechaCreacion(LocalDate.now());
        convocado.setEstado("Activo");
        return convocadoRepository.save(convocado);
    }
    public boolean existsAllByDniRucAndCodigoContratacion(String dniRuc, Long codigo) {
        return convocadoRepository.existsAllByDniRucAndCodigoContratacion(dniRuc, codigo);
    }
}
