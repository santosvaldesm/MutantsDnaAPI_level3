package com.challenge.mutantsDnaAPI.service;

import com.challenge.mutantsDnaAPI.model.DnaRecord;
import com.challenge.mutantsDnaAPI.repository.DnaRecordRepository;
import com.challenge.mutantsDnaAPI.wrapper.StatsWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class DnaRecordService {
    @Autowired
    private DnaRecordRepository dnaRecordRepository;

    public StatsWrapper getStats() {
        long mutanstCount = dnaRecordRepository.countDna(true);
        long humanCount = dnaRecordRepository.countDna(false);
        double ratio = calculateRatio((double) mutanstCount, (double) humanCount);
        return new StatsWrapper(mutanstCount, humanCount, ratio);
    }

    protected double calculateRatio(double mutanstCount, double humanCount) {
        return (humanCount == 0) ? 0 : (mutanstCount / humanCount);
    }

    @Transactional
    public void saveDnaRecord(DnaRecord dnaRecord){
        List<DnaRecord> resultList = dnaRecordRepository.searchDna(dnaRecord.getDna());
        if (resultList.isEmpty()) {
            dnaRecordRepository.save(dnaRecord);
            return;
        }
    }

}
