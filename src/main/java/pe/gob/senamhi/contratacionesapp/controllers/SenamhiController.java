package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;
import pe.gob.senamhi.contratacionesapp.services.SenamhiService;

@RestController
@RequestMapping("/senamhi")
@CrossOrigin(origins = "*")
public class SenamhiController {
    @Autowired
    private SenamhiService senamhiService;
    @GetMapping("/trabajadores/findByDni/{dni}")
    @PreAuthorize("hasAnyAuthority('ROLE_PERSONAL_LOGISTICO', 'ROLE_ADMINISTRADOR')")
    public ResponseEntity<Trabajador> findTrabajadorByDni(@PathVariable("dni")  String dni) {
        return ResponseEntity.ok(senamhiService.findTrabajadorByDni(dni));
    }
    @PostMapping("/trabajadores")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Trabajador> saveTrabajador(@RequestBody Trabajador trabajador) {
        return ResponseEntity.ok(senamhiService.save(trabajador));
    }

}
