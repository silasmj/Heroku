package com.example.heroku.controllers;

import com.example.heroku.models.Dog;
import com.example.heroku.repositories.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DogController {
    @Autowired
    DogRepository dogRepo;

    //Get all Dogs
    @GetMapping("/dogs")
    public List<Dog> getDogs() {
        return dogRepo.findAll();
    }

    //Get Dog by ID
    @GetMapping("/dogs/{id}")
    public Dog getDogById(@PathVariable Long id) {
        return dogRepo.findById(id).get();
    }

    //Creates a Dog
    @PostMapping("/dogs")
    public Dog addDog(@RequestBody Dog newDog) {
        newDog.setId(null);
        return dogRepo.save(newDog);
    }

    //Updates a Dog
    @PutMapping("/dogs/{id}")
    public String updateDogById(@PathVariable Long id, @RequestBody Dog dogToUpdate) {
        return dogRepo.findById(id).map(foundDog -> {
            foundDog.setName(dogToUpdate.getName());
            foundDog.setAge(dogToUpdate.getAge());
            foundDog.setRace(dogToUpdate.getRace());
            foundDog.setWeight(dogToUpdate.getWeight());
            foundDog.setGender(dogToUpdate.getGender());
            dogRepo.save(foundDog);
            return "Dog updated";
        }).orElse("Dog not found");
    }

    //Deletes a Dog
    @DeleteMapping("/dogs/{id}")
    public void deleteDogById(@PathVariable Long id) {
        dogRepo.deleteById(id);
    }
}
