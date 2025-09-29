package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class Member {
    public UUID memberID;
    public String name;
    public String email;
    public String phone;
    public int maxBooksAllowed;
    public List<Book> currentlyIssuedBooks = new ArrayList<>();

    public abstract int getMaxAllowedDays();
    public abstract String getMemberType();

    public int getCurrentlyIssuedCount() {
        return currentlyIssuedBooks.size();
    }
}