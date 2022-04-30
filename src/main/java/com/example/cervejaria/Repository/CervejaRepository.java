package com.example.cervejaria.Repository;

import com.example.cervejaria.Entities.Cerveja;
import org.springframework.data.repository.CrudRepository;


public interface CervejaRepository extends CrudRepository<Cerveja, Integer> {
}
