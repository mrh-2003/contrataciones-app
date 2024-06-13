package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import pe.gob.senamhi.contratacionesapp.dtos.ApiRespuestaDTO;
import pe.gob.senamhi.contratacionesapp.dtos.ConsultaDTO;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SenamhiService {
    @Autowired
    private RestTemplate restTemplate;
    public List<Trabajador> getApiResponse(ConsultaDTO consultaDTO) {
        String url = "http://172.25.0.247:9091/ws-personal-ws/personal/getdepartamento";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<ConsultaDTO> requestEntity = new HttpEntity<>(consultaDTO, headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ApiRespuestaDTO response = restTemplate.postForObject(url, requestEntity, ApiRespuestaDTO.class);
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
        } catch (HttpClientErrorException e) {
            // Manejo de errores del cliente (4xx)
            System.err.println("Error del cliente: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (HttpServerErrorException e) {
            // Manejo de errores del servidor (5xx)
            System.err.println("Error del servidor: " + e.getStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (ResourceAccessException e) {
            // Manejo de errores de acceso a recursos (e.g., problemas de conexión)
            System.err.println("Error de acceso a recurso: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otros errores
            System.err.println("Error inesperado: " + e.getMessage());
        }
        return Collections.emptyList();
    }
}
