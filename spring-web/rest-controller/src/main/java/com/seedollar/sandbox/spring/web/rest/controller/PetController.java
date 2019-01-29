package com.seedollar.sandbox.spring.web.rest.controller;

import com.google.common.collect.Maps;
import com.seedollar.sandbox.spring.web.rest.domain.Pet;
import com.seedollar.sandbox.spring.web.rest.exception.CustomPetException;
import com.seedollar.sandbox.spring.web.rest.exception.DuplicatePetException;
import com.seedollar.sandbox.spring.web.rest.exception.PetNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pets")
public class PetController {

    private static Map<Long, Pet> petMap = Maps.newConcurrentMap();

    @GetMapping("/")
    public List<Pet> getPets() {
        return petMap.values().stream().collect(Collectors.toList());
    }

    @GetMapping("/{petId}")
    public Pet getPet(@PathVariable(name = "petId", required = true) final Long petId) {
        Pet pet = petMap.get(petId);
        if (pet == null) {
            throw new PetNotFoundException(String.format("Pet with id: [%s] not found.", petId));
        }
        return pet;
    }

    @PostMapping(value = "/add", consumes = "application/xml")
    public Pet addPetLegacy(@RequestBody(required = true) Pet newPet) {
        if (!petMap.containsKey(newPet.getId())) {
            petMap.put(newPet.getId(), newPet);
        }
        return petMap.get(newPet.getId());
    }

    @PostMapping(value = "/add", consumes = "application/json")
    public Pet addPet(@RequestBody(required = true) Pet newPet) {
        if (!petMap.containsKey(newPet.getId())) {
            petMap.put(newPet.getId(), newPet);
            return petMap.get(newPet.getId());
        } else {
           throw new DuplicatePetException(String.format("Pet already exists for pet ID: [%s]", newPet.getId()));
        }
    }

    @PutMapping("/update")
    public Pet updatePet(Pet updatedPet) {
        petMap.put(updatedPet.getId(), updatedPet);
        return petMap.get(updatedPet.getId());
    }

    @PatchMapping("/patch")
    public Pet updatePetName(Long petId, String name) {
        Pet pet = petMap.get(petId);
        pet.setName(name);
        return pet;
    }

    @GetMapping("/custom")
    public Pet custom() {
        throw new CustomPetException("Pet custom endpoint not implemented yet");
    }
}
