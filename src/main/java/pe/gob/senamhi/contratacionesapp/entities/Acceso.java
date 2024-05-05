package pe.gob.senamhi.contratacionesapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "accesos")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Acceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private String nombres;
    @Column(nullable = false)
    private String cargo;
    @Column(nullable = false)
    private String dni;
    @Column(nullable = false)
    private String codigoSenamhi;
    @Column(nullable = false)
    private String usuario;
    @Column(nullable = false)
    private String contrasenia;
    @Column(nullable = false)
    private LocalDate fechaCreacion;
    @Column(nullable = false)
    private String estado;
}
