package com.example.Plant.Repository;

import com.example.Plant.Entities.Cerveja;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface CervejaRepository extends CrudRepository<Cerveja, Integer> {
}
