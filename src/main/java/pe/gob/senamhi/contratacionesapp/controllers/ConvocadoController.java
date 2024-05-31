package pe.gob.senamhi.contratacionesapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.gob.senamhi.contratacionesapp.services.ConvocadoService;

@RestController
@RequestMapping("/convocados")
@CrossOrigin(origins = "*")
public class ConvocadoController {
    @Autowired
    private ConvocadoService convocadoService;
}
