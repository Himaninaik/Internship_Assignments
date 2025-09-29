package model;

public class StudentMember extends Member {
    private final int maxAllowedDays = 14;

    {
        this.maxBooksAllowed = 3;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Student";
    }
}