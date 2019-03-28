package com.seedollar.java.spring.servlet.backend.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.seedollar.java.spring.servlet.backend.domain.Workshop;

@RestController
public class WorkshopController {

    private static Map<Long, Workshop> WORKSHOPS_MAP = Map.of(
        11L, Workshop.builder().name("Hammer Slash").address("197 Eaton Road, Eglington, Toronto, G8C2E3").contactNumber("4863868923961").build(),
        12L, Workshop.builder().name("Ride Pipmpin").address("5691 Terry Street, Spadina, Toronto, B7EK6Y").contactNumber("686239682382").build(),
        13L, Workshop.builder().name("Captain Steering").address("68 Carl Road, BlueJays, Toronto, ER31B8").contactNumber("907349909843").build(),
        14L, Workshop.builder().name("Total Engines").address("28184 Vertices Street, Etobicoke, Toronto, J3D1F5").contactNumber("789686234775").build(),
        15L, Workshop.builder().name("Gleem").address("9479 Umber Street, Missasauga, Toronto, 9C40H4").contactNumber("3907345697826").build()
    );

    @GetMapping("/workshops/{workshopId}")
    public ResponseEntity<Workshop> getWorkshop(@PathVariable("workshopId") long workshopId)
        throws InterruptedException {
        Thread.sleep(500);
        return ResponseEntity.ok(WORKSHOPS_MAP.get(workshopId));
    }
}
