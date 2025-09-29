package model;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Queue;

public class Book {
    public int bookId;
    public String title;
    public String author;
    public String genre;
    public boolean isIssued;
    public Member issuedTo;
    public LocalDate dueDate;
    public Queue<Member> reservationQueue = new LinkedList<>();
}