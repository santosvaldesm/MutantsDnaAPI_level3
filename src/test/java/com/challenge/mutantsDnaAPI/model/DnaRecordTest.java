package com.challenge.mutantsDnaAPI.model;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class DnaRecordTest {

    @Test
    protected void testConstructrWhenAlwaysThenSetValues(){        
        //Arrange
        int id = 1001;
        String dna = "ACCF,ACCF,ACCF,ACCF" ;
        boolean isMutant = true; 
        //Act        
        DnaRecord aDnaRecord = new DnaRecord(id,dna,isMutant);
        //Assert
        assertEquals(aDnaRecord.getDna(),dna);
        assertEquals(aDnaRecord.getId(),id);
        assertEquals(aDnaRecord.getIsMutant(),isMutant);
    }

    @Test
    protected void testSetValuesWhenAlwaysThenShouldObtainSameValues(){        
        //Arrange
        int id = 1001;
        String dna = "ACCF,ACCF,ACCF,ACCF" ;
        boolean isMutant = true; 
        DnaRecord aDnaRecord = new DnaRecord();        
        //Act        
        aDnaRecord.setDna(dna);
        aDnaRecord.setId(id);
        aDnaRecord.setIsMutant(isMutant);
        //Assert
        assertEquals(aDnaRecord.getDna(),dna);
        assertEquals(aDnaRecord.getId(),id);
        assertEquals(aDnaRecord.getIsMutant(),isMutant);
    }
}
