package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pe.gob.senamhi.contratacionesapp.dtos.ApiRespuestaDTO;
import pe.gob.senamhi.contratacionesapp.dtos.ConsultaDTO;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenamhiService {
    @Autowired
    private RestTemplate restTemplate;
    public List<Trabajador> getApiResponse(ConsultaDTO consultaDTO) {
        String url = "http://172.25.0.247:9091/ws-personal-ws/personal/getdepartamento";
        // Configurar los encabezados de la solicitud
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Crear una entidad HttpEntity con el objeto ConsultaDTO y los encabezados
        HttpEntity<ConsultaDTO> requestEntity = new HttpEntity<>(consultaDTO, headers);

        // Enviar la solicitud POST y obtener la respuesta
        ApiRespuestaDTO response = restTemplate.postForObject(url, requestEntity, ApiRespuestaDTO.class);

        // Procesar la respuesta y mapear los datos a la lista de Trabajador
        if (response != null && response.getEstado() == 1) {
            return response.getListPersonal().stream()
                    .map(personal -> new Trabajador(
                            personal.getDni(),
                            personal.getNombre(),
                            personal.getApePaterno(),
                            personal.getApeMaterno(),
                            personal.getCodigoEmpleado(),
                            personal.getCodigoZonal(),
                            personal.getZonal(),
                            personal.getCodigoCargo(),
                            personal.getCargo()
                    ))
                    .collect(Collectors.toList());
        }
        return null;
    }
}
