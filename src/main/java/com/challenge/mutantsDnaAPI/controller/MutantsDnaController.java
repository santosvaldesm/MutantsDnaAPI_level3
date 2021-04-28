package com.challenge.mutantsDnaAPI.controller;

import com.challenge.mutantsDnaAPI.model.DnaRecord;
import com.challenge.mutantsDnaAPI.service.DnaRecordService;
import com.challenge.mutantsDnaAPI.service.MutantsServices;
import com.challenge.mutantsDnaAPI.wrapper.DnaWrapper;
import com.challenge.mutantsDnaAPI.wrapper.StatsWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("")
public class MutantsDnaController {
    
    @Autowired
    DnaRecordService dnaRecordService;

    @Autowired
    MutantsServices mutantsServices;    

    @GetMapping("/stats")
    public StatsWrapper stats() {      
        return dnaRecordService.getStats();
    }

    @GetMapping("/address")
    public String address() {      
        return mutantsServices.getServerAddress();
    }
   
    @RequestMapping(value = "/mutant/", method = RequestMethod.POST, consumes = "application/json")
    @ResponseBody
    public ResponseEntity<?> saveDna(@RequestBody DnaWrapper aDnaWrapper) {        
        boolean isMutant = mutantsServices.isMutant(aDnaWrapper.getDna());        
        DnaRecord dnaRecord = new DnaRecord(0,Arrays.deepToString(aDnaWrapper.getDna()),isMutant);
        dnaRecordService.saveDnaRecord(dnaRecord);        
        return isMutant ? new ResponseEntity<>(HttpStatus.OK) : 
                          new ResponseEntity<>(HttpStatus.FORBIDDEN);        
    }

}
