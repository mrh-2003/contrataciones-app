package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.services.AccesoService;

import java.util.List;

@RestController
@RequestMapping("/accesos")
@CrossOrigin(origins = "*")
public class AccesoController {
    @Autowired
    private AccesoService accesoService;

    @GetMapping
    public ResponseEntity<List<Acceso>> findAll() {
        return ResponseEntity.ok(accesoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Acceso> findById(@PathVariable("id")  Long id) {
        return ResponseEntity.ok(accesoService.findById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id")  Long id) {
        accesoService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<Acceso> save(@RequestBody Acceso acceso) {
        return ResponseEntity.ok(accesoService.save(acceso));
    }
    @PutMapping
    public ResponseEntity<Acceso> update(@RequestBody Acceso acceso) {
        return ResponseEntity.ok(accesoService.save(acceso));
    }


}
