package com.challenge.mutantsDnaAPI.model;

import javax.persistence.*;

@Entity
@Table(name = "dna_records")
@NamedQuery(name = "DnaRecord.findByDna",query = "SELECT d FROM DnaRecord d WHERE d.dna = :dna")
public class DnaRecord {
    private int id;
    private String dna;
    private boolean isMutant;
    
    public DnaRecord() {
    }

    public DnaRecord(int id, String dna, boolean isMutant) {
        this.id = id;
        this.dna = dna;        
        this.isMutant = isMutant;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean getIsMutant() {
        return isMutant;
    }

    public void setIsMutant(boolean isMutant) {
        this.isMutant = isMutant;
    }
    
}
