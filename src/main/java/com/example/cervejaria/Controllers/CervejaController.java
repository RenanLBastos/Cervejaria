package com.example.cervejaria.Controllers;

import com.example.cervejaria.Entities.Cerveja;
import com.example.cervejaria.Repository.CervejaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class CervejaController {

    private final CervejaRepository cervejaRepository;


    public CervejaController(final CervejaRepository cervejaRepository) {
        this.cervejaRepository = cervejaRepository;
    }

    @GetMapping("/cervejas/api/v1")
    public Iterable<Cerveja> getAllCervejas() {
        return this.cervejaRepository.findAll();
    }

    @GetMapping("/cerveja/{id}/api/v1")
    public Optional<Cerveja> getCervejaById(@PathVariable("id") Integer id) {
        return this.cervejaRepository.findById(id);
    }

    @PostMapping("/cervejas/api/v1")
    public Cerveja createNewCerveja(@RequestBody Cerveja cerveja) {
        Cerveja cerveja1 = new Cerveja();
        Iterable<Cerveja> cervejaList = getAllCervejas();
        for (Cerveja cervejaObj : cervejaList) {
            if (cerveja.getNome().equals(cervejaObj.getNome())) {
                cerveja1.setNome("Cerveja ja cadastrada");
                return cerveja1;
            }
        }
        return this.cervejaRepository.save(cerveja);
    }

    @PutMapping("/cervejas/{id}/api/v1")
    public Cerveja updateCerveja(@PathVariable("id") Integer id, @RequestBody Cerveja p) {

        Optional<Cerveja> cervejaToUpdateOptional = this.cervejaRepository.findById(id);
        if (null != cervejaToUpdateOptional && !cervejaToUpdateOptional.isPresent()) {
            return null;
        }
        Cerveja cervejaToUpdate = cervejaToUpdateOptional.get();
        if (p.getAbv() != null) {
            cervejaToUpdate.setAbv(p.getAbv());
        }
//        if (p.getQuantity() != null) {
//            cervejaToUpdate.setQuantity(p.getQuantity());
//        }
//        if (p.getNome() != null) {
//            cervejaToUpdate.setName(p.getName());
//        }
//        if (p.getWateringFrequency() != null) {
//            cervejaToUpdate.setWateringFrequency(p.getWateringFrequency());
//        }
//        Plant updatedPlant = this.cervejaRepository.save(cervejaToUpdate);
        return cervejaToUpdate;
    }

    @DeleteMapping("/cervejas/{id}")
    public Cerveja deleteCerveja(@PathVariable("id") Integer id) {
        Optional<Cerveja> cervejaToDeleteOptional =
                this.cervejaRepository.findById(id);
        if (!cervejaToDeleteOptional.isPresent()) {
            return null;
        }
        Cerveja cerveja = cervejaToDeleteOptional.get();
        this.cervejaRepository.delete(cerveja);
        return cerveja;
    }
}
