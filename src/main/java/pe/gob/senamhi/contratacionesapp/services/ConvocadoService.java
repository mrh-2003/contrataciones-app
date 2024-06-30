package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Contratacion;
import pe.gob.senamhi.contratacionesapp.entities.Convocado;
import pe.gob.senamhi.contratacionesapp.repositories.IContratacionRepository;
import pe.gob.senamhi.contratacionesapp.repositories.IConvocadoRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ConvocadoService {
    @Autowired
    private IConvocadoRepository convocadoRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private IContratacionRepository contratacionRepository;
    public List<Convocado> findAllByCodigoContratacion(Long codigo) {
        return convocadoRepository.findAllByCodigoContratacion(codigo);
    }
    public Convocado save(Convocado convocado) {
        convocado.setFechaCreacion(LocalDate.now());
        convocado.setEstado("Activo");
        convocado = convocadoRepository.save(convocado);
        Contratacion contra = contratacionRepository.findById(convocado.getCodigoContratacion()).orElse(new Contratacion());
        String fechaHora = LocalDate.now() + " " + LocalTime.now().toString().split("\\.")[0];
        String body = "<html>" +
                "<body>" +
                "<table style='border-collapse: collapse; width: 100%;'>" +
                "<tr>" +
                "<td style='padding: 10px; border: 3px double black;'>N° de Expediente:</td>" +
                "<td style='padding: 10px; border: 3px double black;'>" +contra.getNumeroExpediente() +  "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='padding: 10px; border: 3px double black;'>Servicio/Bien:</td>" +
                "<td style='padding: 10px; border: 3px double black;'>"+ contra.getDescripcion() + "</td>" +
                "</tr>" +
                "<tr>" +
                "<td style='padding: 10px; border: 3px double black;'>Nombre del Proveedor:</td>" +
                "<td style='padding: 10px; border: 3px double black;'> " + convocado.getNombre() + " " + convocado.getApellidoPaterno() +" " + convocado.getApellidoMaterno() + " </td>" +
                "</tr>" +
                "<tr>" +
                "<td style='padding: 10px; border: 3px double black;'>Fecha y Hora de Postulación:</td>" +
                "<td style='padding: 10px; border: 3px double black;'> " + fechaHora+" </td>" +
                "</tr>" +
                "</table>" +
                "</body>" +
                "</html>";
        try {
            emailService.sendHtmlEmail(convocado.getCorreo(), "POSTULACIÓN REGISTRADA CON ÉXITO.", body);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return convocado;
    }
    public boolean existsAllByDniRucAndCodigoContratacion(String dniRuc, Long codigo) {
        return !convocadoRepository.findAllByDniRucAndCodigoContratacion(dniRuc, codigo).isEmpty();
    }
}
