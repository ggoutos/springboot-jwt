package com.example.demo.repository;

import com.example.demo.domain.Unit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    Slice<Unit> findAllByTitleAndRegion(String title, String region, Pageable pageable);

}
