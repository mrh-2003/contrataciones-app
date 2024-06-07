package pe.gob.senamhi.contratacionesapp.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "trabajadores")
public class Trabajador {
    @Id
    private String dni;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apePaterno;
    @Column(nullable = false)
    private String apeMaterno;
    @Column(nullable = false)
    private String codigoEmpleado;
    @Column(nullable = false)
    private String codigoZonal;
    @Column(nullable = false)
    private String zonal;
    @Column(nullable = false)
    private String codigoCargo;
    @Column(nullable = false)
    private String cargo;
}