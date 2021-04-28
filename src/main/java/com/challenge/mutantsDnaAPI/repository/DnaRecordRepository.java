package com.challenge.mutantsDnaAPI.repository;

import com.challenge.mutantsDnaAPI.model.DnaRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DnaRecordRepository extends JpaRepository<DnaRecord, Integer> {
    
    @Query("SELECT COUNT(d) FROM DnaRecord d WHERE d.isMutant = ?1")
    long countDna(boolean isMutant);

    @Query("SELECT d FROM DnaRecord d WHERE d.dna = ?1")
    List<DnaRecord> searchDna(String dna);
}