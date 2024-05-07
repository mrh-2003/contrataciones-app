package pe.gob.senamhi.contratacionesapp.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "contrataciones")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Contratacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @ManyToOne
    private Formato formato;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String tipoServicio;
    @Column(nullable = false)
    private LocalDate fechaPublicacion;
    @Column(nullable = false)
    private LocalDate fechaVencimiento;
    @Column(nullable = false)
    private String codigoSenamhi;
    @Column(nullable = false)
    private String codigoSede;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private String estado;
    @Column(nullable = false)
    private String dniRuc;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private String apellidoPaterno;
    @Column(nullable = false)
    private String apellidoMaterno;
    @Column(nullable = false)
    private String direccion;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String correo;
}
