package com.challenge.mutantsDnaAPI.controller;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import com.challenge.mutantsDnaAPI.model.DnaRecord;
import com.challenge.mutantsDnaAPI.service.DnaRecordService;
import com.challenge.mutantsDnaAPI.service.MutantsServices;
import com.challenge.mutantsDnaAPI.wrapper.DnaWrapper;
import com.challenge.mutantsDnaAPI.wrapper.StatsWrapper;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;

@SpringBootTest
public class MutantsDnaControllerTests {
    
    @InjectMocks 
    private MutantsDnaController aMutantsDnaController;

    @Spy
    private DnaRecordService aDnaRecordService;  

    @Spy
    private MutantsServices aMutantsServices;  

    @Test
    protected void testSaveDnaWhenDnaIsMutantThenReturnHttpStatusOK(){        
        //Arrange
        DnaWrapper aDnaWrapper = new DnaWrapper(); 
        String[] aDna = {"ACCF","ACCF","ACCF","ACCF"};        
        aDnaWrapper.setDna(aDna);
        Mockito.doReturn(true).when(aMutantsServices).isMutant(aDna);
        Mockito.doNothing().when(aDnaRecordService).saveDnaRecord(Mockito.any(DnaRecord.class));
        //Act        
        ResponseEntity<?> result = aMutantsDnaController.saveDna(aDnaWrapper);
        //Assert
        verify(aMutantsServices, Mockito.times(1)).isMutant(aDna);
        verify(aDnaRecordService, Mockito.times(1)).saveDnaRecord(Mockito.any(DnaRecord.class));
        assertEquals(result,new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    protected void testSaveDnaWhenDnaIsHumanThenReturnHttpStatusFORBIDDEN(){        
        //Arrange
        DnaWrapper aDnaWrapper = new DnaWrapper(); 
        String[] aDna = {"ACCF","ACCF","ACCF","ACCF"};        
        aDnaWrapper.setDna(aDna);
        Mockito.doReturn(false).when(aMutantsServices).isMutant(aDna);
        Mockito.doNothing().when(aDnaRecordService).saveDnaRecord(Mockito.any(DnaRecord.class));
        //Act        
        ResponseEntity<?> result = aMutantsDnaController.saveDna(aDnaWrapper);
        //Assert
        verify(aMutantsServices, Mockito.times(1)).isMutant(aDna);
        verify(aDnaRecordService, Mockito.times(1)).saveDnaRecord(Mockito.any(DnaRecord.class));
        assertEquals(result,new ResponseEntity<>(HttpStatus.FORBIDDEN));
    }

    @Test
    protected void testStatsWhenAlwaysThenCallGetStatsFunction(){        
        //Arrange        
        StatsWrapper aStatsWrapperReturned = new StatsWrapper(10,20,2);        
        Mockito.doReturn(aStatsWrapperReturned).when(aDnaRecordService).getStats();
        //Act        
        StatsWrapper aStatsWrapperResult = aMutantsDnaController.stats();
        //Assert        
        verify(aDnaRecordService, Mockito.times(1)).getStats();
        assertEquals(aStatsWrapperResult.getCount_human_dna(),aStatsWrapperReturned.getCount_human_dna());
        assertEquals(aStatsWrapperResult.getCount_mutant_dna(),aStatsWrapperReturned.getCount_mutant_dna());
        assertEquals(aStatsWrapperResult.getRatio(),aStatsWrapperReturned.getRatio());
    }


    @Test
    protected void testAddressWhenAlwaysThenCallGetServerAddressFunction(){        
        //Arrange        
        String anAdrresExpected = "Current IP: 1.1.1.1";        
        Mockito.doReturn(anAdrresExpected).when(aMutantsServices).getServerAddress();
        //Act        
        String anAdrresResult = aMutantsDnaController.address();
        //Assert        
        verify(aMutantsServices, Mockito.times(1)).getServerAddress();
        assertEquals(anAdrresExpected,anAdrresResult);
    }

}
