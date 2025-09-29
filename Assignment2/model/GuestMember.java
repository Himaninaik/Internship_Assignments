package model;

public class GuestMember extends Member {
    private final int maxAllowedDays = 7;

    {
        this.maxBooksAllowed = 1;
    }

    @Override
    public int getMaxAllowedDays() {
        return maxAllowedDays;
    }

    @Override
    public String getMemberType() {
        return "Guest";
    }
}