package com.example.cervejaria.Repository;

import com.example.cervejaria.Entities.Receita;
import org.springframework.data.repository.CrudRepository;


public interface ReceitaRepository extends CrudRepository<Receita, Integer> {
}
