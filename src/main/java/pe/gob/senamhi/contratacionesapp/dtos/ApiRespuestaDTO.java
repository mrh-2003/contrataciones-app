package pe.gob.senamhi.contratacionesapp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRespuestaDTO {
    private int estado;
    private String mensaje;
    private List<Personal> listPersonal;
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Personal {
        private String dni;
        private String apePaterno;
        private String apeMaterno;
        private String nombre;
        private String fechaNacimiento;
        private String codigoSexo;
        private String codigoEmpleado;
        private String correo;
        private String telefono;
        private String codigoDependencia;
        private String dependencia;
        private String codigoUnidad;
        private String unidad;
        private String abreUnidad;
        private String codigoCargo;
        private String cargo;
        private String codigoZonal;
        private String zonal;
    }
}
