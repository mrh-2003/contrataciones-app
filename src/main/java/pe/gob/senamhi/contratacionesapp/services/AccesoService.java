package pe.gob.senamhi.contratacionesapp.services;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.entities.Role;
import pe.gob.senamhi.contratacionesapp.dtos.AccesoDTO;
import pe.gob.senamhi.contratacionesapp.repositories.IRoleRepository;
import pe.gob.senamhi.contratacionesapp.repositories.IAccesoRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AccesoService implements UserDetailsService {

	@Autowired
	IAccesoRepository accesoRepository;
	
	@Autowired
	IRoleRepository roleRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Acceso acceso = accesoRepository.findByUsuario(username);
	     return new org.springframework.security.core.userdetails.User(acceso.getUsuario(), acceso.getContrasenia(),acceso.getEstado() , true, true, true,  mapRolesToAuthorities(acceso.getRoles()));
	}
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	public Acceso save(AccesoDTO userRegisteredDTO) {
		ModelMapper m = new ModelMapper();
		Acceso acceso = m.map(userRegisteredDTO, Acceso.class);
		Role role = new Role();
		if(userRegisteredDTO.getRol().equals("USUARIO"))
		  	role = roleRepo.findByRole("ROLE_USUARIO");
		else if(userRegisteredDTO.getRol().equals("ADMINISTRADOR"))
		 	role = roleRepo.findByRole("ROLE_ADMINISTRADOR");
		acceso.setContrasenia(passwordEncoder.encode(userRegisteredDTO.getContrasenia()));
		acceso.setRole(role);
		acceso.setFechaCreacion(LocalDate.now());
		return accesoRepository.save(acceso);
	}
	public Acceso update(AccesoDTO userRegisteredDTO) {
		Acceso acceso = accesoRepository.findById(userRegisteredDTO.getCodigo()).orElse(null);
		if(acceso == null) return null;
		acceso.setEstado(userRegisteredDTO.getEstado());
		if(userRegisteredDTO.getContrasenia().length() <= 10)
			acceso.setContrasenia(passwordEncoder.encode(acceso.getDni()));
		return accesoRepository.save(acceso);
	}

	public Acceso findById(Long id) {
		return accesoRepository.findById(id).orElse(null);
	}
	public Acceso findByNombreUsuario(String username) {
		return accesoRepository.findByUsuario(username);
	}

	public void deleteById(Long id) {
		accesoRepository.deleteById(id);
	}

	public List<Acceso> findAll() {
		return accesoRepository.findAll(Sort.by(Sort.Direction.DESC, "codigo"));
	}
}
