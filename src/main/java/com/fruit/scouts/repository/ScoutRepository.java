package com.fruit.scouts.repository;

import com.fruit.scouts.model.Scout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoutRepository extends JpaRepository<Scout, Long> { }
