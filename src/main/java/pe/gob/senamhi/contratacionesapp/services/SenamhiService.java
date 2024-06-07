package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Trabajador;
import pe.gob.senamhi.contratacionesapp.repositories.ITrabajadorRepository;

@Service
public class SenamhiService {
    @Autowired
    private ITrabajadorRepository trabajadorRepository;
    public Trabajador findTrabajadorByDni(String dni) {
        return trabajadorRepository.findByDni(dni);
    }
    public Trabajador save(Trabajador trabajador) {
        return trabajadorRepository.save(trabajador);
    }
   }
