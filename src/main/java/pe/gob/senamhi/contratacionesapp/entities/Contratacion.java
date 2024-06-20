package pe.gob.senamhi.contratacionesapp.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "contrataciones")
public class Contratacion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
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
    private String sede;
    @Column(nullable = false)
    private Long codigoAcceso;
    @Column(nullable = false)
    private String urlFormato;
    @Column(nullable = false)
    private String urlConvocatoria;
    private String urlResultado;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private String estado;
    private String numeroExpediente;
}
