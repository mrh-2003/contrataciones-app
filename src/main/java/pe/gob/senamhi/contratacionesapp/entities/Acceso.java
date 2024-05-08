package pe.gob.senamhi.contratacionesapp.entities;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
@Data
@Entity
@Table(name= "accesos")
public class Acceso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	@Column(nullable = false, unique = true)
    private String usuario;
	@Column(nullable = false)
    private String contrasenia;
	@Column(nullable = false)
	private String nombres;
	@Column(nullable = false)
	private String cargo;
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
