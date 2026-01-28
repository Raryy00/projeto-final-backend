package com.rarysson.carreira_futebol.repository;

import com.rarysson.carreira_futebol.entity.Time;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {
    List<Time> findByLiga(String liga);
}