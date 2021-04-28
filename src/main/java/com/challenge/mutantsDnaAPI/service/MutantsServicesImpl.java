package com.challenge.mutantsDnaAPI.service;

import org.springframework.stereotype.Service;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
public class MutantsServicesImpl implements MutantsServices {

    static final int SECUENCE_SIZE = 3;

    public String getServerAddress() {
        try {            
            return "Current IP: " + InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return e.getMessage();
        }
    }
    
    public boolean isMutant(String dna[]) {
        int sequencesFound = 0;
        for (int positionY = 0; positionY < dna.length; positionY++) {
            for (int positionX = 0; positionX < dna.length; positionX++) {
                sequencesFound = hasHorizontalSequence(dna, positionX, positionY) ? ++sequencesFound : sequencesFound;
                sequencesFound = hasVerticalSequence(dna, positionX, positionY) ? ++sequencesFound : sequencesFound;
                sequencesFound = hasRightObliqueSequence(dna, positionX, positionY) ? ++sequencesFound : sequencesFound;
                sequencesFound = haseLeftObliqueSequence(dna, positionX, positionY) ? ++sequencesFound : sequencesFound;
                if (sequencesFound > 1) {
                    return true;
                }
            }
        }
        return false;
    }

    protected boolean hasHorizontalSequence(String dna[], int positionX, int positionY) {
        if ((positionX + SECUENCE_SIZE) < dna.length) {
            char aChar = getDnaPositionXY(dna, positionX, positionY);
            if (   sameCharacterInXY(aChar, dna, ++positionX, positionY)
                && sameCharacterInXY(aChar, dna, ++positionX, positionY)
                && sameCharacterInXY(aChar, dna, ++positionX, positionY)) { 
                return true;
            }
        }
        return false;
    }

    protected boolean hasVerticalSequence(String dna[], int positionX, int positionY) {
        if ((positionY + SECUENCE_SIZE) < dna.length) {
            char aChar = getDnaPositionXY(dna, positionX, positionY);
            if (   sameCharacterInXY(aChar, dna, positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, positionX, ++positionY)) {
                return true;
            }
        }
        return false;
    }

    protected boolean hasRightObliqueSequence(String dna[], int positionX, int positionY) {
        if ((positionX + SECUENCE_SIZE) < dna.length && (positionY + SECUENCE_SIZE) < dna.length) {
            char aChar = getDnaPositionXY(dna, positionX, positionY);
            if (   sameCharacterInXY(aChar, dna, ++positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, ++positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, ++positionX, ++positionY)) {
                return true;
            }
        }
        return false;
    }
    
    protected boolean haseLeftObliqueSequence(String dna[], int positionX, int positionY) {
        if ((positionX - SECUENCE_SIZE) >= 0 && (positionY + SECUENCE_SIZE) < dna.length) {
            char aChar = getDnaPositionXY(dna, positionX, positionY);
            if (   sameCharacterInXY(aChar, dna, --positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, --positionX, ++positionY)
                && sameCharacterInXY(aChar, dna, --positionX, ++positionY)) {
                return true;
            }
        }
        return false;
    }

    protected char getDnaPositionXY(String dna[], int positionX, int positionY) {
        return dna[positionY].charAt(positionX);
    }

    protected boolean sameCharacterInXY(char aChar, String dna[], int positionX, int positionY) {
        return dna[positionY].charAt(positionX) == aChar;
    }
}