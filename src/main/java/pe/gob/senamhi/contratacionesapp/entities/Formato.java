package pe.gob.senamhi.contratacionesapp.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "formatos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Formato {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigo;
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String url;
    @Column(nullable = false)
    private String nombre;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private LocalDate fechaEdicion;
    @Column(nullable = false)
    private String estado;
}
