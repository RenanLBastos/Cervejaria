package com.example.cervejaria.repository;

import com.example.cervejaria.dto.Receita;
import com.example.cervejaria.dto.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {
}
