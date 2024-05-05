package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.senamhi.contratacionesapp.entities.Proveedor;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;
import pe.gob.senamhi.contratacionesapp.services.SenamhiService;

@RestController
@RequestMapping("/senamhi")
@CrossOrigin(origins = "*")
public class SenamhiController {
    @Autowired
    private SenamhiService senamhiService;
    @GetMapping("/trabajadores/findByDni/{dni}")
    public ResponseEntity<Trabajador> findTrabajadorByDni(@PathVariable("dni")  String dni) {
        return ResponseEntity.ok(senamhiService.findTrabajadorByDni(dni));
    }
    @GetMapping("/proveedores/findByDniRuc/{dniRuc}")
    public ResponseEntity<Proveedor> findProveedorByDniRuc(@PathVariable("dniRuc")  String dniRuc) {
        return ResponseEntity.ok(senamhiService.findProveedorByDniRUc(dniRuc));
    }

}
