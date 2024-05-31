package pe.gob.senamhi.contratacionesapp.entities;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name="roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long codigo;
	@Column(nullable = false, unique = true)
	private String role;
	public Role() {
		super();
	}
	public Role(String role) {
		super();
		this.role = role;
	}
}