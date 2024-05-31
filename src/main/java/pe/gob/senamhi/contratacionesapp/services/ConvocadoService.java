package pe.gob.senamhi.contratacionesapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.repositories.IConvocadoRepository;

@Service
public class ConvocadoService {
    @Autowired
    private IConvocadoRepository convocadoRepository;
}
