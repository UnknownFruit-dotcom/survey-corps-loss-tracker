package com.fruit.scouts.repository;

import com.fruit.scouts.model.Team;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @EntityGraph(attributePaths = {"leader"})
    List<Team> findAll();
}
