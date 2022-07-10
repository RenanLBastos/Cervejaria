package com.example.cervejaria.controller;

import com.example.cervejaria.config.JwtTokenUtil;
import com.example.cervejaria.request.JwtRequest;
import com.example.cervejaria.response.JwtResponse;
import com.example.cervejaria.dto.UsuarioDto;
import com.example.cervejaria.exception.ApiError;
import com.example.cervejaria.request.UsuarioRequest;
import com.example.cervejaria.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class UsuarioController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UsuarioService usuarioService;

    @CrossOrigin(origins = "http://localhost:4200/", maxAge = 3600)
    @PostMapping(value = "/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final UserDetails userDetails = usuarioService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @PostMapping("/registrar/api/v1")
    public ResponseEntity<ApiError> createNewUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return this.usuarioService.createNewusuario(usuarioRequest);
    }

    @GetMapping("/usuarios/api/v1")
    public Iterable<UsuarioDto> getAllUsuarios() {
        return this.usuarioService.getAllUsuarios();
    }

    @GetMapping("/usuario/{id}/api/v1")
    public Optional<UsuarioDto> getUsuarioById(@PathVariable("id") Integer id) {
        return this.usuarioService.getUsuarioById(id);
    }

    @GetMapping("/usuario/api/v1/{email}")
    public Optional<UsuarioDto> getUsuarioByEmail(@PathVariable("email") String email) {
        return this.usuarioService.getUsuarioByEmail(email);
    }

    @PutMapping("/usuario/{id}/api/v1")
    public ResponseEntity<ApiError> updateUsuario(@PathVariable("id") Integer id, @RequestBody UsuarioRequest p) {
        return this.usuarioService.updateUsuario(id, p);
    }

    @DeleteMapping("/usuario/{id}/api/v1")
    public ResponseEntity<ApiError> deleteUsuario(@PathVariable("id") Integer id) {
        return this.usuarioService.deleteusuario(id);
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
