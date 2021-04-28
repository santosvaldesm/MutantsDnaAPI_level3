package com.challenge.mutantsDnaAPI.wrapper;

import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

@SpringBootTest
public class StatsWrapperTests {

    @Test
    protected void testConstructrWhenAlwaysThenSetValues(){        
        //Arrange
        long humans = 100;
        long mutants = 200 ;
        double ratio = 0.5; 
        //Act        
        StatsWrapper aStatsWrapper = new StatsWrapper(mutants,humans,ratio);
        //Assert
        assertEquals(aStatsWrapper.getCount_mutant_dna(),mutants);
        assertEquals(aStatsWrapper.getCount_human_dna(),humans);
        assertEquals(aStatsWrapper.getRatio(),ratio);
    }

    @Test
    protected void testSetValuesWhenAlwaysThenShouldObtainSameValues(){        
        //Arrange
        long humans = 100;
        long mutants = 200 ;
        double ratio = 0.5; 
        StatsWrapper aStatsWrapper = new StatsWrapper();        
        //Act        
        aStatsWrapper.setCount_human_dna(humans);
        aStatsWrapper.setCount_mutant_dna(mutants);
        aStatsWrapper.setRatio(ratio);
        //Assert
        assertEquals(aStatsWrapper.getCount_mutant_dna(),mutants);
        assertEquals(aStatsWrapper.getCount_human_dna(),humans);
        assertEquals(aStatsWrapper.getRatio(),ratio);
    }
}
