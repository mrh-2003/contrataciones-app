package pe.gob.senamhi.contratacionesapp.controllers;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.gob.senamhi.contratacionesapp.config.JwtGeneratorValidator;
import pe.gob.senamhi.contratacionesapp.dtos.AccesoSummaryDTO;
import pe.gob.senamhi.contratacionesapp.entities.Acceso;
import pe.gob.senamhi.contratacionesapp.dtos.AccesoDTO;
import pe.gob.senamhi.contratacionesapp.repositories.IAccesoRepository;
import pe.gob.senamhi.contratacionesapp.services.AccesoService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/accesos")
@CrossOrigin(origins = "*")

public class AccesoController {
	@Autowired
	IAccesoRepository userRepo;
	@Autowired
	AuthenticationManager authManager;
	@Autowired
	JwtGeneratorValidator jwtGenVal;
	@Autowired
	BCryptPasswordEncoder bcCryptPasswordEncoder;
	@Autowired
	AccesoService accesoService;
	@PostMapping("/registration")
	public ResponseEntity<Object> registerUser(@RequestBody AccesoDTO accesoDto) {
		Acceso users =  accesoService.save(accesoDto);
		if (users.equals(null))
			return generateRespose("Not able to save user ", HttpStatus.BAD_REQUEST, accesoDto);
		else
			return generateRespose("User saved successfully : " + users.getCodigo(), HttpStatus.OK, users);
	}
	@PostMapping("/genToken")
	public ResponseEntity<Map<String, Object>> generateJwtToken(@RequestBody AccesoDTO accesoDto) throws Exception {
		
			Authentication authentication = authManager.authenticate(
					new UsernamePasswordAuthenticationToken(accesoDto.getUsuario(), accesoDto.getContrasenia()));
			SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwtToken =  jwtGenVal.generateToken(authentication);
		Claims claims = jwtGenVal.extractUserRole(jwtToken);
		Map<String, Object> response = new HashMap<>();
		response.put("token", jwtToken);
		response.put("role", claims.get("role"));
		return ResponseEntity.ok(response);
	}
	public ResponseEntity<Object> generateRespose(String message, HttpStatus st, Object responseobj) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("meaasge", message);
		map.put("Status", st.value());
		map.put("data", responseobj);

		return new ResponseEntity<Object>(map, st);
	}
	@GetMapping("/protectedRoute/{token}")
	public ResponseEntity<Boolean> protectedRoute(@PathVariable("token") String token) {
		try {
			String username = jwtGenVal.extractUsername(token);
			Acceso user = userRepo.findByUsuario(username);
			if (user == null) {
				return ResponseEntity.ok(false);
			}
			if (jwtGenVal.validateToken(token, username)) {
				return ResponseEntity.ok(true);
			} else {
				return ResponseEntity.ok(false);
			}
		} catch (MalformedJwtException e) {
			return ResponseEntity.ok(false);
		}
	}
	@GetMapping("/protectedRole/{token}")
	public ResponseEntity<Boolean> protectedRouteRol(@PathVariable("token") String token) {
		try {
			Claims claims = jwtGenVal.extractUserRole(token);
			if (claims == null) {
				return ResponseEntity.ok(false);
			}
			if(claims.get("role").equals("ROLE_ADMINISTRADOR")) {
				return ResponseEntity.ok(true);
			}
			return ResponseEntity.ok(false);
		} catch (MalformedJwtException e) {
			return ResponseEntity.ok(false);
		}
	}
	@GetMapping("/getAcceso/{token}")
	public ResponseEntity<AccesoSummaryDTO> getAccesoByToken(@PathVariable("token") String token) {
		try {
			ModelMapper m = new ModelMapper();
			String username = jwtGenVal.extractUsername(token);
			Acceso user = userRepo.findByUsuario(username);
			if (user == null) {
				return ResponseEntity.ok(null);
			}
			AccesoSummaryDTO userDTO = m.map(user, AccesoSummaryDTO.class);
			return ResponseEntity.ok(userDTO);
		} catch (MalformedJwtException e) {
			return ResponseEntity.ok(null);
		}
	}
	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	public ResponseEntity<List<Acceso>> findAll() {
		return ResponseEntity.ok(accesoService.findAll());
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	public ResponseEntity<Acceso> findById(@PathVariable("id")  Long id) {
		return ResponseEntity.ok(accesoService.findById(id));
	}
	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	public ResponseEntity<Void> deleteById(@PathVariable("id")  Long id) {
		accesoService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	@PutMapping
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	public ResponseEntity<Acceso> update(@RequestBody AccesoDTO acceso) {
		return ResponseEntity.ok(accesoService.update(acceso));
	}
}
