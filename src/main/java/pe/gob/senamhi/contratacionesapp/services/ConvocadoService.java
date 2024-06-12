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
    @Autowired
    private EmailService emailService;
    public List<Convocado> findAllByCodigoContratacion(Long codigo) {
        return convocadoRepository.findAllByCodigoContratacion(codigo);
    }
    public Convocado save(Convocado convocado) {
        convocado.setFechaCreacion(LocalDate.now());
        convocado.setEstado("Activo");
        emailService.sendSimpleEmail(convocado.getCorreo(), "Confirmación de postulación", "Usted ha sido convocado");
        return convocadoRepository.save(convocado);
    }
    public boolean existsAllByDniRucAndCodigoContratacion(String dniRuc, Long codigo) {
        return !convocadoRepository.findAllByDniRucAndCodigoContratacion(dniRuc, codigo).isEmpty();
    }
}
