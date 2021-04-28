package com.challenge.mutantsDnaAPI.service;

import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.challenge.mutantsDnaAPI.model.DnaRecord;
import com.challenge.mutantsDnaAPI.repository.DnaRecordRepository;
import com.challenge.mutantsDnaAPI.wrapper.StatsWrapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.util.ArrayList;
import java.util.List;


@SpringBootTest
public class DnaRecordServiceTests {
    
    @InjectMocks 
    private DnaRecordService aDnaRecordService;

    @Spy
    private DnaRecordRepository aDnaRecordRepository;

    @Test
    protected void testGetStatsWhenAlwaysThenReturnAStatsWraoerWithResults(){        
        //Arrange
        Mockito.doReturn((long)7).when(aDnaRecordRepository).countDna(true);
        Mockito.doReturn((long)2).when(aDnaRecordRepository).countDna(false);        
        //Act        
        StatsWrapper aStatsWrapper = aDnaRecordService.getStats();
        //Assert
        assertEquals(aStatsWrapper.getCount_human_dna(), 2);
        assertEquals(aStatsWrapper.getCount_mutant_dna(), 7);
        assertEquals(aStatsWrapper.getRatio(), 3.5);
    }

    @Test
    protected void testCalculateRatioWhenHumansIsZeroCountThenReturnZero(){
        //Act        
        double result = aDnaRecordService.calculateRatio(100,0);
        //Assert
        assertEquals(result, 0);
    }

    @Test
    protected void testCalculateRatioWhenHumansIsDiferenThanZeroCountThenDivideMutantsByHumans(){
        //Act        
        double result = aDnaRecordService.calculateRatio(50,100);
        //Assert
        assertEquals(result, 0.5);
    }

    @Test
    protected void testSaveDnaRecordWhenExistsDnaThenCallSaveMethod(){
       //Arrange
       DnaRecord aDnaRecord = new DnaRecord();             
       Mockito.doReturn(new ArrayList<DnaRecord>()).when(aDnaRecordRepository).searchDna(Mockito.any(String.class));
       Mockito.doReturn(null).when(aDnaRecordRepository).save(Mockito.any(DnaRecord.class));
       //Act        
       aDnaRecordService.saveDnaRecord(aDnaRecord);
       //Assert
       verify(aDnaRecordRepository, Mockito.times(1)).save(Mockito.any(DnaRecord.class));
    }
    
    @Test
    protected void testSaveDnaRecordWhenExistsDnaThenDoNotCallSaveMethod(){
       //Arrange
       DnaRecord aDnaRecord = new DnaRecord();             
       String aDna = "[ACCF,ACCF,ACCF]";
       aDnaRecord.setDna(aDna);
       List<DnaRecord> aResultList = new ArrayList<DnaRecord>();
       aResultList.add(aDnaRecord);
       Mockito.doReturn(aResultList).when(aDnaRecordRepository).searchDna(aDna);
       Mockito.doReturn(null).when(aDnaRecordRepository).save(Mockito.any(DnaRecord.class));
       //Act        
       aDnaRecordService.saveDnaRecord(aDnaRecord);
       //Assert
       verify(aDnaRecordRepository, Mockito.times(0)).save(Mockito.any(DnaRecord.class));
    }

}
