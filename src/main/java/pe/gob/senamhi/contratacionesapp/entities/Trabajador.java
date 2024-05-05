package pe.gob.senamhi.contratacionesapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "trabajadores")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Trabajador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    private String dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String codigoSenamhi;
    private String codigoSede;
    private String sede;
    private String codigoCargo;
    private String cargo;
}
