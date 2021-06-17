package com.challenger.alkemy.api.warmup.security.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.challenger.alkemy.api.warmup.models.dto.UsuarioDto;
import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.security.dto.JwtDto;
import com.challenger.alkemy.api.warmup.security.dto.LoginUsuario;
import com.challenger.alkemy.api.warmup.security.jwt.JwtProvider;
import com.challenger.alkemy.api.warmup.services.UsuarioService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService usuarioService;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/sign-up")
	public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDto usuarioDto, BindingResult result) {

		if (result.hasErrors()) {
			return validar(result);
		}

		usuarioDto.setPassword(passwordEncoder.encode(usuarioDto.getPassword()));

		Usuario usuarioEntity = new Usuario();
		BeanUtils.copyProperties(usuarioDto, usuarioEntity);

		try {
			usuarioService.save(usuarioEntity);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}

		return ResponseEntity.status(HttpStatus.CREATED).build();

	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginUsuario loginData) {

		final Optional<Usuario> usuarioLogin = usuarioService.findUsuarioByEmail(loginData.getEmail());

		if (!usuarioLogin.isPresent()) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no existe, debe registrarse");

		}

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginData.getEmail(), loginData.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtProvider.generateToken(usuarioLogin.get());
		JwtDto jwtDto = new JwtDto(jwt);

		return new ResponseEntity<>(jwtDto, HttpStatus.OK);
	}

	public ResponseEntity<?> validar(BindingResult result) {
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo " + err.getField() + " " + err.getDefaultMessage());
		});

		return ResponseEntity.badRequest().body(errores);
	}
}
