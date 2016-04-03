package model;/* Authored by Kushagra on 3/28/2016. */

public class ViolationEntry {
    private int minorViolationCount;
    private int majorViolationCount;
    private int severeViolationCount;

    public ViolationEntry() {
        this.minorViolationCount = 0;
        this.majorViolationCount = 0;
        this.severeViolationCount = 0;
    }

    public ViolationEntry(int v1, int v2, int v3) {
        this.minorViolationCount = v1;
        this.majorViolationCount = v2;
        this.severeViolationCount = v3;
    }

    public int getMinorViolationCount() {
        return minorViolationCount;
    }

    public void setMinorViolationCount(int minorViolationCount) {
        this.minorViolationCount = minorViolationCount;
    }

    public int getMajorViolationCount() {
        return majorViolationCount;
    }

    public void setMajorViolationCount(int majorViolationCount) {
        this.majorViolationCount = majorViolationCount;
    }

    public int getSevereViolationCount() {
        return severeViolationCount;
    }

    public void setSevereViolationCount(int severeViolationCount) {
        this.severeViolationCount = severeViolationCount;
    }
    
    public double calculatePenaltyScore(ViolationEntry entry)
    {
    	double penaltyScore = 0d;
    	penaltyScore = entry.minorViolationCount + (100 * entry.majorViolationCount) + 
    			(1000 * entry.severeViolationCount);
		return penaltyScore;
    }

    @Override
    public String toString() {
        String comma = ",";
        String openingBracket = "(";
        String closingBracket = ")";

        return openingBracket + minorViolationCount + comma +
                majorViolationCount + comma + severeViolationCount + closingBracket;
    }
}
