package pe.gob.senamhi.contratacionesapp.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.entities.Role;
import pe.gob.senamhi.contratacionesapp.dtos.UserDTO;
import pe.gob.senamhi.contratacionesapp.repositories.IRoleRepository;
import pe.gob.senamhi.contratacionesapp.repositories.IUserRepository;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUserRepository userRepo;
	
	@Autowired
	IRoleRepository roleRepo;
	
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Acceso acceso = userRepo.findByUserName(username);
	     return new org.springframework.security.core.userdetails.User(acceso.getUsuario(), acceso.getContrasenia(), mapRolesToAuthorities(acceso.getRoles()));
	}
	
	public Collection<? extends GrantedAuthority> mapRolesToAuthorities(Set<Role> roles){
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
	}

	public Acceso save(UserDTO userRegisteredDTO) {
		Role role = new Role();
		if(userRegisteredDTO.getRole().equals("USER"))
		  role = roleRepo.findByRole("ROLE_USER");
		else if(userRegisteredDTO.getRole().equals("ADMIN"))
		 role = roleRepo.findByRole("ROLE_ADMIN");
		Acceso acceso = new Acceso();
		acceso.setUsuario(userRegisteredDTO.getUserName());
		acceso.setContrasenia(passwordEncoder.encode(userRegisteredDTO.getPassword()));
		acceso.setRole(role);
		
		return userRepo.save(acceso);
	}
}
