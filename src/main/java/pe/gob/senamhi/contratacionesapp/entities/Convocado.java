package pe.gob.senamhi.contratacionesapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "convocados")
public class Convocado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
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
    private String correo;
    @Column(nullable = false)
    private String telefono;
    @Column(nullable = false)
    private String urlPDF;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private Boolean estado;
}