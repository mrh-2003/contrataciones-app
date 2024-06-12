package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.gob.senamhi.contratacionesapp.dtos.ConsultaDTO;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;
import pe.gob.senamhi.contratacionesapp.services.SenamhiService;
import pe.gob.senamhi.contratacionesapp.services.TrabajadorService;

import java.util.List;

@RestController
@RequestMapping("/senamhi")
@CrossOrigin(origins = "*")
public class SenamhiController {
    @Autowired
    private TrabajadorService trabajadorService;
    @Autowired
    private SenamhiService senamhiService;
    @GetMapping("/trabajadores/findByDni/{dni}")
    @PreAuthorize("hasAnyAuthority('ROLE_PERSONAL_LOGISTICO', 'ROLE_ADMINISTRADOR')")
    public ResponseEntity<Trabajador> findTrabajadorByDni(@PathVariable("dni")  String dni) {
        return ResponseEntity.ok(trabajadorService.findTrabajadorByDni(dni));
    }
    @PostMapping("/trabajadores")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Trabajador> saveTrabajador(@RequestBody Trabajador trabajador) {
        return ResponseEntity.ok(trabajadorService.save(trabajador));
    }

    @PostMapping("/trabajadores/consulta")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Trabajador> getApiResponse(@RequestBody ConsultaDTO consultaDTO) {
        List<Trabajador> trabajadores = senamhiService.getApiResponse(consultaDTO);
        if (trabajadores == null || trabajadores.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(trabajadores.get(0));
    }
}
