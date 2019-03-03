package com.example.demo.controller;

import com.example.demo.domain.Unit;
import com.example.demo.service.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController()
@RequestMapping("/units")
public class UnitController {

    @Autowired
    UnitService unitService;

    @GetMapping("")
    public ResponseEntity all() {
        return ok(this.unitService.findAll());
    }

    @GetMapping("/findByTitleAndRegion")
    public ResponseEntity findByTitleAndRegionOrderByScoreAsc(@RequestParam String title, @RequestParam String region) {
        Optional<List<Unit>> list = this.unitService.findAllByTitleAndRegionOrderByScoreAsc(title, region);

        return ok(list
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "No units found for the requested parameters.")));
    }


}
