package feature_parsers;/* Authored by Kushagra on 4/11/2016. */

import model.AllViolationData;
import model.ViolationEntry;
import parser.AllViolationParser;
import utils.Date;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PastPenalty {

    public static void main(String[] args) throws IOException {

        List<AllViolationData> entries = AllViolationParser.readViolationData();
        Map<Integer, Double> map_serialID_pastViolation = buildPastViolationsMap(entries);

        System.out.println(map_serialID_pastViolation.toString());
    }

    private static Map<Integer, Double> buildPastViolationsMap(List<AllViolationData> entries) {
        Map<Integer, Double> map = new HashMap<>();

        for (AllViolationData entry : entries) {

            int penaltyCount = 0;
            int totalPenaltyScore = 0;

            String restID = entry.getRestaurantID();
            Date date = entry.getDate();

            for (AllViolationData lookupEntry : entries) {

                if (entry != lookupEntry) {

                    String currentRestID = lookupEntry.getRestaurantID();
                    Date currentDate = lookupEntry.getDate();

                    if (restID.equals(currentRestID)) {

                        if (Date.isEarlier(date, currentDate)) {
                            totalPenaltyScore += calculateScore(lookupEntry.getViolationEntry());
                            penaltyCount++;
                        }
                    }
                }
            }

            if (penaltyCount != 0) {
                map.put(entry.getSerialID(), (double) totalPenaltyScore / (double) penaltyCount);
            } else {
                map.put(entry.getSerialID(), 0d);   // TODO: ignore or use zero ?
            }
        }

        return map;
    }

    private static int calculateScore(ViolationEntry violationEntry) {

        int v1 = violationEntry.getMinorViolationCount();
        int v2 = violationEntry.getMinorViolationCount();
        int v3 = violationEntry.getSevereViolationCount();

        return (v1 + v2 + v3);
    }
}
