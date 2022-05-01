package com.example.cervejaria.repository;

import com.example.cervejaria.dto.Receita;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceitaRepository extends CrudRepository<Receita, Integer> {
}
