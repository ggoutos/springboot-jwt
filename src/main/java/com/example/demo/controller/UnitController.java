package com.example.demo.controller;

import com.example.demo.service.UnitService;
import com.example.demo.utils.RestResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController()
@RequestMapping("/units")
public class UnitController {

    @Autowired
    UnitService unitService;

    @GetMapping("")
    public ResponseEntity all() {
        return RestResponseBuilder.build(this.unitService.findAll());
    }

    @GetMapping("/findByTitleAndRegion")
    public ResponseEntity findByTitleAndRegion(@RequestParam String title, @RequestParam String region, Pageable pageable) {
        return RestResponseBuilder.build(this.unitService.findAllByTitleAndRegion(title, region, pageable));
    }

}
