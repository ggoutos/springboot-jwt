package com.example.demo.repository;

import com.example.demo.domain.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Optional<List<Unit>> findAllByTitleAndRegionOrderByScoreAsc(String title, String region);

}
