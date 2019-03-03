package com.example.demo.service;

import com.example.demo.domain.Unit;
import com.example.demo.repository.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnitService {

    @Autowired
    UnitRepository unitRepository;

    public Optional<List<Unit>> findAllByTitleAndRegionOrderByScoreAsc(String title, String region) {
        return unitRepository.findAllByTitleAndRegionOrderByScoreAsc(title, region);
    }

    public List<Unit> findAll() {
        return unitRepository.findAll();
    }

}
