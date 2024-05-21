package pe.gob.senamhi.contratacionesapp.dtos;

import lombok.Data;

import java.time.LocalDate;
@Data
public class AccesoSummaryDTO {
    private Long codigo;
    private String usuario;
    private String codigoSede;
    private String sede;
    private String codigoSenamhi;
}
