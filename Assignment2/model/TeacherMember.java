package model;

public class TeacherMember extends Member {
    private final int maxAllowedDays = 30;

    {
        this.maxBooksAllowed = 5;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Teacher";
    }
}