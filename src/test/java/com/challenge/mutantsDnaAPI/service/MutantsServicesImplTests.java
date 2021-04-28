package com.challenge.mutantsDnaAPI.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MutantsServicesImplTests {
    
    @InjectMocks
    MutantsServicesImpl aMutantsServicesImpl;

    @Spy
    MutantsServicesImpl aSpyMutantsServicesImpl;

    @Test
	void testGetDnaPositionXYWhenAlwaysThenReturnCharOfXYposition(){
        //Arrange   
        String dna[] = new String[]{"ATTTT","CGTAA","CTGAC","CGAGT","CATCG"};               
        //Act
        char resultA = aMutantsServicesImpl.getDnaPositionXY(dna,0,0);
        char resultT = aMutantsServicesImpl.getDnaPositionXY(dna,1,0);
        char resultC = aMutantsServicesImpl.getDnaPositionXY(dna,0,1);
        char resultG = aMutantsServicesImpl.getDnaPositionXY(dna,1,1);
        //Assert
        assertEquals(resultA, 'A');       
        assertEquals(resultT, 'T');       
        assertEquals(resultC, 'C');       
        assertEquals(resultG, 'G');       
    }

    @Test
	void testSameCharacterInXYWhenIsSameCharInNotPositionXYThenReturFalse() { 
        //Arrange  
        String dna[] = new String[]{"ATTTT","CGTAA","CTGAC","CGAGT","CATCG"};
        char aChar = 'A';        
        int positionX = 0;
        int positionY = 1;        
        //Act
        boolean result = aMutantsServicesImpl.sameCharacterInXY(aChar,dna,positionX,positionY);
        //Assert
        assertFalse(result);        
	}

    @Test
	void testSameCharacterInXYWhenIsNotSameCharInPositionXYThenReturnTrue() {
        //Arrange       
        String dna[] = new String[]{"ATTTT","CGTAA","CTGAC","CGAGT","CATCG"};   
        char aChar = 'C';        
        int positionX = 0;
        int positionY = 1;
        //Act        
        boolean result = aMutantsServicesImpl.sameCharacterInXY(aChar,dna,positionX,positionY);
        //Assert
        assertTrue(result);       
	}       

    @Test
    void testIsMutantWhenDnaHasOneSequenceThenReturnFalse(){
        //Arrange        
        String dna[] = new String[]{"ATTTT","GCTAA","CTATC","CGAGC","CATCG"};
        //Act
        boolean result = aMutantsServicesImpl.isMutant(dna);
        //Assert
        assertFalse(result);
    }

    @Test
    void testIsMutantWhenDnaHasRigthObliqueSeqAndHasLeftObliqueSeqThenReturnTrue(){
        //Arrange        
        String dna[] = new String[]{"ATATC","CATAA","GTAAC","TGAAT","CATCG"};
        //Act
        boolean result = aMutantsServicesImpl.isMutant(dna);
        //Assert
        assertTrue(result);
    }

    @Test
    void testIsMutantWhenDnaHasVerticalSequenceAndHasHorizontalSequenceThenReturnTrue(){
        //Arrange        
        String dna[] = new String[]{"ATTTT","CCTAA","CTGAC","CGAGT","CATCG"};
        //Act
        boolean result = aMutantsServicesImpl.isMutant(dna);
        //Assert
        assertTrue(result);
    }

    @Test
    void testIsMutantWhenDnaIsNotMutantTheEvaluateAllPositions() {
        //Arrange
        
        String dna[] = new String[]{"ATTG","AGTG","GCTG","CTCA"};
        int elementsNumber = dna.length * dna.length;
        //Act
        boolean result = aSpyMutantsServicesImpl.isMutant(dna);        
        //Assert
        verify(aSpyMutantsServicesImpl, Mockito.times(elementsNumber)).hasVerticalSequence(Mockito.any(String[].class),Mockito.any(int.class),Mockito.any(int.class));
        verify(aSpyMutantsServicesImpl, Mockito.times(elementsNumber)).hasHorizontalSequence(Mockito.any(String[].class),Mockito.any(int.class),Mockito.any(int.class));
        verify(aSpyMutantsServicesImpl, Mockito.times(elementsNumber)).hasRightObliqueSequence(Mockito.any(String[].class),Mockito.any(int.class),Mockito.any(int.class));
        verify(aSpyMutantsServicesImpl, Mockito.times(elementsNumber)).haseLeftObliqueSequence(Mockito.any(String[].class),Mockito.any(int.class),Mockito.any(int.class));
        assertFalse(result);
    }

}
