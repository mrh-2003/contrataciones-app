package pe.gob.senamhi.contratacionesapp.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "accesos")
public class Acceso {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	@Column(nullable = false, unique = true)
    private String usuario;
	@Column(nullable = false)
    private String contrasenia;
	@Column(nullable = false)
	private String nombres;
	@Column(nullable = false)
	private String codigoCargo;
	@Column(nullable = false)
	private String cargo;
	@Column(nullable = false)
	private String codigoSede;
	@Column(nullable = false)
	private String sede;
	@Column(nullable = false)
	private String dni;
	@Column(nullable = false)
	private String codigoSenamhi;
	@Column(nullable = false)
	private LocalDate fechaCreacion;
	@Column(nullable = false)
	private Boolean estado;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "accesos_role", joinColumns = @JoinColumn(name = "acceso_codigo", referencedColumnName = "codigo"),
	inverseJoinColumns = @JoinColumn(name = "role_codigo", referencedColumnName = "codigo") )
	Set<Role> roles = new HashSet<Role>();
	public void setRole(Role role) {
		this.roles.add(role);
	}
}
