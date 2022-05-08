package com.example.cervejaria.repository;

import com.example.cervejaria.dto.UsuarioDto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioDto, Integer> {

    UsuarioDto findByUsername(String username);
}
