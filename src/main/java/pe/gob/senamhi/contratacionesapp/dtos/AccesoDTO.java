package pe.gob.senamhi.contratacionesapp.dtos;

import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDate;

@Data
public class AccesoDTO {
    private Long codigo;
	private String usuario;
    private String contrasenia;
    private String rol;
    private String nombres;
    private String codigoCargo;
    private String cargo;
    private String codigoSede;
    private String sede;
    private String dni;
    private String codigoSenamhi;
    private LocalDate fechaCreacion;
    private Boolean estado;
}

